<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizzeria Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
    <style>
        body { font-family: 'Roboto', sans-serif; background-color: #f8f8f8; scroll-behavior: smooth; color: #333; }
        .navbar { background-color: #c62828; padding: 10px 0; }
        .navbar .nav-link, .navbar .navbar-brand { color: white !important; }
        .hero { 
            background: url('https://img.freepik.com/premium-photo/top-view-assorted-junk-food-like-pizza-burgers-fries_641503-88050.jpg') no-repeat center center/cover; 
            height: 450px; display: flex; align-items: center; justify-content: center; text-align: center; color: white; font-size: 2.5em; font-weight: bold; text-shadow: 2px 2px 5px rgba(0,0,0,0.5); 
        }
        .hero .btn { margin-top: 20px; }
        #about, #contact { background-color: white; padding: 60px 20px; margin: 40px auto; max-width: 1000px; border-radius: 10px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); text-align: center; }
        #about ul { list-style: none; padding: 0; text-align: left; max-width: 600px; margin: 0 auto; }
        #about ul li { padding: 10px 0; font-size: 1.1em; color: #555; }
        #about ul li i { color: #c62828; margin-right: 10px; }
        .card img { height: 180px; object-fit: cover; }
        .scrolling-wrapper { overflow-x: auto; overflow-y: hidden; white-space: nowrap; padding-bottom: 10px; }
        .scrolling-wrapper .row { flex-wrap: nowrap; }
        .scrolling-wrapper .col-md-3 { flex: 0 0 auto; }
        footer { background-color: #c62828; color: white; padding: 30px 0; text-align: center; }
        .footer-links a { color: white; margin: 0 10px; text-decoration: none; }
    </style>
</head>
<body>

<header>
<nav class="navbar navbar-expand-lg shadow-sm">
    <div class="container-fluid px-0">

        <a class="navbar-brand d-flex align-items-center ps-3 pe-3" href="home.jsp">
            <img src="https://static.vecteezy.com/system/resources/previews/011/157/905/original/pizzeria-emblem-on-blackboard-pizza-logo-template-emblem-for-cafe-restaurant-or-food-delivery-service-free-vector.jpg" alt="Pizzeria Logo" height="50" class="me-2">
            <span class="fw-bold">Pizzeria</span>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="#about">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#menu">Menu</a></li>
                <li class="nav-item"><a class="nav-link" href="/ui/cart"><i class="fas fa-shopping-cart"></i> Cart</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="loginDropdown" role="button" data-bs-toggle="dropdown">Login</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/ui/users/login"><i class="fas fa-user"></i> User</a></li>
                        <li><a class="dropdown-item" href="/ui/admins/login"><i class="fas fa-user-shield"></i> Admin</a></li>
                    </ul>
                </li>
                <li class="nav-item"><a class="nav-link" href="#contact">Contact</a></li>
            </ul>
        </div>
    </div>
</nav>
</header>

<div class="hero" data-aos="fade-up">
    Order Your Favorite Pizza Online!!!!
    <br>
    <a href="#menu" class="btn btn-light btn-lg">View Menu</a>
</div>

<section id="about">
    <h2>Why Choose Pizzeria?</h2>
    <p>Freshly baked pizzas, fast delivery, and the best ingredients!</p>
    <ul>
        <li><i class="fas fa-check-circle"></i> Wide variety of pizzas and sides</li>
        <li><i class="fas fa-check-circle"></i> Online order tracking</li>
        <li><i class="fas fa-check-circle"></i> Secure payment options</li>
        <li><i class="fas fa-check-circle"></i> Customer-friendly service</li>
    </ul>
</section>

<div class="container my-5" id="menu">
    <h2 class="text-center mb-4">Our Menu</h2>
    <div class="scrolling-wrapper">
        <div class="row flex-nowrap">
            <%-- Example Menu Items, can be replaced with dynamic content --%>
            <% String[] items = {"Margherita", "Pepperoni", "Veggie Delight", "Garlic Bread", "Coke"}; %>
            <% String[] images = {
                "https://www.dominos.co.in/files/items/Double_Cheese_Margherita.jpg",
                "https://www.dominos.co.in/files/items/Farmhouse.jpg",
                "https://www.dominos.co.in/files/items/Deluxe_Veggie.jpg",
                "https://www.dominos.co.in/files/items/stuffed-garlic-breadstick_1346070564.webp",
                "https://tse4.mm.bing.net/th/id/OIP.1qhcyWB6_lU3XVbl3wTaxgHaHr?rs=1&pid=ImgDetMain&o=7&rm=3"
            }; %>
            <% for(int i=0;i<items.length;i++){ %>
            <div class="col-md-3 mb-4" data-aos="zoom-in">
                <div class="card shadow-sm">
                    <img src="<%= images[i] %>" class="card-img-top" alt="Menu Item">
                    <div class="card-body text-center">
                        <h5 class="card-title"><%= items[i] %></h5>
                        <a href="order.jsp" class="btn btn-sm btn-primary">Order Now</a>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>

<section id="contact">
    <h2>Contact Us</h2>
    <p>Have questions? Reach out to us!</p>
    <div class="row">
        <div class="col-md-6">
            <h5><i class="fas fa-map-marker-alt me-2"></i>Our Address</h5>
            <p>123 Pizza Street, Bangalore</p>
            <h5><i class="fas fa-envelope me-2"></i>Email</h5>
            <p>support@pizzeria.com</p>
            <h5><i class="fas fa-phone me-2"></i>Phone</h5>
            <p>+91 98765 43210</p>
        </div>
        <div class="col-md-6">
            <form method="POST" action="ContactServlet">
                <div class="mb-3">
                    <input type="text" class="form-control" name="name" placeholder="Your Name" required>
                </div>
                <div class="mb-3">
                    <input type="email" class="form-control" name="email" placeholder="Your Email" required>
                </div>
                <div class="mb-3">
                    <textarea class="form-control" rows="4" name="message" placeholder="Your Message" required></textarea>
                </div>
                <button type="submit" class="btn btn-danger w-100">Send Message</button>
            </form>
        </div>
    </div>
</section>

<footer>
    <div class="footer-links mb-3">
        <a href="#about">About</a> |
        <a href="#menu">Menu</a> |
        <a href="#contact">Contact</a>
    </div>
    <p>&copy; 2025 Pizzeria. All Rights Reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script>
    AOS.init({ duration: 1000, once: true });
</script>
</body>
</html>
