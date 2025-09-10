package com.pizza.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.admin.config.JwtUtil;
import com.pizza.admin.dto.AdminDTO;
import com.pizza.admin.entity.Admin;
import com.pizza.admin.entity.Branch;
import com.pizza.admin.repository.AdminRepository;
import com.pizza.admin.repository.BranchRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private BranchRepository branchRepo;

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

        if (admin.getUsername() != null) {
            existing.setUsername(admin.getUsername());
        }
        if (admin.getEmail() != null) {
            existing.setEmail(admin.getEmail());
        }
        if (admin.getPhoneNumber() != null) {
            existing.setPhoneNumber(admin.getPhoneNumber());
        }
        if (admin.getPassword() != null) {
            existing.setPassword(admin.getPassword());
        }
        if (admin.getAdminRole() != null) {
            existing.setAdminRole(admin.getAdminRole());
        }

        // ✅ Correct branch update
        if (admin.getBranch() != null && admin.getBranch().getId() != null) {
            Branch branch = branchRepo.findById(admin.getBranch().getId())
                    .orElseThrow(() -> new RuntimeException("Branch not found!"));
            existing.setBranch(branch);
        }

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

    
    public Admin toEntity(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setUsername(dto.getUsername());
        admin.setEmail(dto.getEmail());
        admin.setPhoneNumber(dto.getPhoneNumber());
        admin.setPassword(dto.getPassword());
        admin.setAdminRole(dto.getAdminRole());
        admin.setActive(dto.isActive());

        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());  // lightweight reference
            admin.setBranch(branch);
        }

        return admin;
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

