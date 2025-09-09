package com.pizza.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.admin.config.JwtUtil;
import com.pizza.admin.dto.AdminDTO;
import com.pizza.admin.entity.Admin;
import com.pizza.admin.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private JwtUtil jwtUtil;

    // ---------------- Login ----------------
    public Optional<Admin> login(String username, String password) {
        // Just match username and plain password
        return adminRepo.findByUsernameAndPassword(username, password);
    }

    // ---------------- Add Admin ----------------
    public Admin addNewAdmin(Admin admin) {
        admin.setAdminRole(Admin.Role.ADMIN);
        // store password as plain text
        admin.setActive(true); 
        return adminRepo.save(admin);
    }

    // ---------------- Update Admin ----------------
    public Admin updateAdmin(Admin admin) {
        Admin existing = adminRepo.findById(admin.getId())
                .orElseThrow(() -> new RuntimeException("Admin not found!"));

        existing.setPhoneNumber(admin.getPhoneNumber());
        existing.setBranch(admin.getBranch());
        existing.setActive(admin.isActive());
        return adminRepo.save(existing);
    }

    // ---------------- Get All Admins ----------------
    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    // ---------------- Activate/Deactivate ----------------
    public void deactivateAdmin(Long id) {
        Admin admin = adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found!"));
        admin.setActive(false);
        adminRepo.save(admin);
    }

    public void activateAdmin(Long id) {
        Admin admin = adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setActive(true);
        adminRepo.save(admin);
    }

    // ---------------- Admin DTO ----------------
    public AdminDTO toDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setUsername(admin.getUsername());
        dto.setEmail(admin.getEmail());
        dto.setPhoneNumber(admin.getPhoneNumber());
        dto.setAdminRole(admin.getAdminRole());
        dto.setActive(admin.isActive());
        if (admin.getBranch() != null) {
            dto.setBranchId(admin.getBranch().getId());
            dto.setBranchName(admin.getBranch().getName());
            dto.setBranchAddress(admin.getBranch().getAddress());
        }
        return dto;
    }

    // ---------------- Other Methods ----------------
    public Optional<AdminDTO> getAdminById(Long id) {
        return adminRepo.findById(id)               // returns Optional<Admin>
                        .map(this::toDTO);          // convert Admin → AdminDTO
    }

    public void deleteAdmin(Long id) {
        adminRepo.deleteById(id);
    }

    public boolean canDeleteBranch(Long branchId) {
        return adminRepo.findByBranchId(branchId).isEmpty();
    }

    public List<AdminDTO> getAdminsByBranch(Long branchId) {
        return adminRepo.findByBranchId(branchId).stream()
                .map(this::toDTO)
                .toList();
    }

    public boolean branchHasAdmins(Long branchId) {
        return !adminRepo.findByBranchId(branchId).isEmpty();
    }
    
    public AdminDTO getAdminDTOById(Long id) {
        return adminRepo.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }
    

    public AdminDTO getAdminFromToken(String token) {
        Long adminId = jwtUtil.getAdminIdFromToken(token);
        return getAdminById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }

}





//package com.pizza.admin.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.pizza.admin.dto.AdminDTO;
//import com.pizza.admin.entity.Admin;
//import com.pizza.admin.repository.AdminRepository;
//
//@Service
//public class AdminService{
//	
//	@Autowired
//    private AdminRepository adminRepo;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//
//	public Optional<Admin> login(String username, String rawPassword) {
//	    return adminRepo.findByUsername(username)
//	        .filter(admin -> passwordEncoder.matches(rawPassword, admin.getPassword()));
//	}	    
//
//	    public Admin addNewAdmin(Admin admin) {
//	        admin.setAdminRole(Admin.Role.ADMIN);
//	        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//	        return adminRepo.save(admin);
//	    }
//
//	
//	    public Admin updateAdmin(Admin admin) {
//	        Admin existing = adminRepo.findById(admin.getId())
//	                .orElseThrow(() -> new RuntimeException("Admin not found!"));
//	
//	        // Allowed fields to update
//	        existing.setPhoneNumber(admin.getPhoneNumber());
//	        existing.setBranch(admin.getBranch());
//	        existing.setActive(admin.isActive());
//	        return adminRepo.save(existing);
//	    }
//	    
//
//	    public List<Admin> getAllAdmins() {
//	    	List<Admin> admins = adminRepo.findAll();
//	    	admins.forEach(a -> {
//	    	    System.out.println("Admin: " + a.getUsername() + " -> Role: " + a.getAdminRole());
//	    	});
//	
//	        return adminRepo.findAll();
//	    }
//
//
//	    public void deactivateAdmin(Long id) {
//	        Admin admin = adminRepo.findById(id)
//	                .orElseThrow(() -> new RuntimeException("Admin not found!"));
//	        admin.setActive(false);
//	        adminRepo.save(admin);
//	    }
//	    
//
//	    public void activateAdmin(Long id) {
//	        Admin admin = adminRepo.findById(id)
//	                         .orElseThrow(() -> new RuntimeException("Admin not found"));
//	        admin.setActive(true);
//	        adminRepo.save(admin);
//	    }
//	    
//	    public AdminDTO getAdminDTOById(Long id) {
//	        return adminRepo.findById(id).map(this::toDTO)
//	                .orElseThrow(() -> new RuntimeException("Admin not found"));
//	    }
//	   
//	    public boolean canDeleteBranch(Long branchId) {
//	        List<Admin> admins = adminRepo.findByBranchId(branchId);
//	        return admins.isEmpty();  // true = can delete, false = only deactivate
//	    }
//
//	    public List<AdminDTO> getAdminsByBranch(Long branchId) {
//	        return adminRepo.findByBranchId(branchId).stream()
//	                .map(this::toDTO)
//	                .toList();
//	    }
//	    
//
//	    public boolean branchHasAdmins(Long branchId) {
//	        return !adminRepo.findByBranchId(branchId).isEmpty();
//	    }
//	
//
//	    public void deleteAdmin(Long id) {
//	        adminRepo.deleteById(id);
//	    }
//	    
//	    public Optional<Admin> getAdminById(Long id) {
//	        return adminRepo.findById(id);
//	    }
//
//
//	    public AdminDTO toDTO(Admin admin) {
//	        AdminDTO dto = new AdminDTO();
//	        dto.setId(admin.getId());
//	        dto.setUsername(admin.getUsername());
//	        dto.setEmail(admin.getEmail());
//	        dto.setPhoneNumber(admin.getPhoneNumber());
//	        dto.setAdminRole(admin.getAdminRole());
//	        dto.setActive(admin.isActive());
//	        if (admin.getBranch() != null) {
//	            dto.setBranchId(admin.getBranch().getId());
//	        }
//	        return dto;
//	    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package com.pizza.admin.service;
////
////import java.util.List;
////import java.util.Optional;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Service;
////
////import com.pizza.admin.entiry.Admin;
////import com.pizza.admin.repository.AdminRepository;
////
////import jakarta.servlet.http.HttpSession;
////
////
////@Service
////public class AdminServiceImpl implements AdminService {
////
////    @Autowired
////    private AdminRepository adminRepo;
////    
////    @Override
////    public Optional<Admin> login(String username, String password) {
////        return adminRepo.findByUsernameAndPassword(username, password);
////    }
////
////    @Override
////    public Admin getAdmin(HttpSession session) {
////        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
////        return admin;
////    }
////
////
////  
////    @Override
////    public Admin updateAdmin(Admin admin) {
////        Admin existing = adminRepo.findById(admin.getId())
////                .orElseThrow(() -> new RuntimeException("Admin not found!"));
////
////        // Allowed fields to update
////        existing.setPhoneNumber(admin.getPhoneNumber());
////        existing.setBranch(admin.getBranch());
////        existing.setActive(admin.isActive());
////        return adminRepo.save(existing);
////    }
////    
////    @Override
////    public Admin addNewAdmin(Admin admin) {
////        admin.setAdminRole(Admin.Role.ADMIN); 
////        return adminRepo.save(admin);
////    }
////    
////    @Override
////    public List<Admin> getAllAdmins() {
////    	List<Admin> admins = adminRepo.findAll();
////    	admins.forEach(a -> {
////    	    System.out.println("Admin: " + a.getUsername() + " -> Role: " + a.getAdminRole());
////    	});
////
////        return adminRepo.findAll();
////    }
////
////    @Override
////    public Optional<Admin> getAdminById(Long id) {
////        return adminRepo.findById(id);
////    }
////    
////    @Override
////    public void deactivateAdmin(Long id) {
////        Admin admin = adminRepo.findById(id)
////                .orElseThrow(() -> new RuntimeException("Admin not found!"));
////        admin.setActive(false);
////        adminRepo.save(admin);
////    }
////    
////    @Override
////    public void activateAdmin(Long id) {
////        Admin admin = adminRepo.findById(id)
////                         .orElseThrow(() -> new RuntimeException("Admin not found"));
////        admin.setActive(true);
////        adminRepo.save(admin);
////    }
////
////    @Override
////    public boolean canDeleteBranch(Long branchId) {
////        List<Admin> admins = adminRepo.findByBranchId(branchId);
////        return admins.isEmpty();  // true = can delete, false = only deactivate
////    }
////
////    @Override
////    public List<Admin> getAdminsByBranch(Long branchId) {
////        return adminRepo.findByBranchId(branchId);
////    }
////
////    @Override
////    public boolean branchHasAdmins(Long branchId) {
////        return !adminRepo.findByBranchId(branchId).isEmpty();
////    }
////
////    @Override
////    public void deleteAdmin(Long id) {
////        adminRepo.deleteById(id);
////    }
////
////}
//
//
//
