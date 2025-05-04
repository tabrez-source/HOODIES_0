import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class StatusApp {
    
    public static void main(String[] args) throws IOException {
        // Create an HTTP server on port 8090
        HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
        
        // Create a context for the root path
        server.createContext("/", new RootHandler());
        
        // Create a context for the /status path
        server.createContext("/status", new StatusHandler());

        // Create contexts for product pages
        server.createContext("/product/1", new ProductHandler("Classic Hoodie", 49.99, "Our best-selling classic hoodie with premium fabric.", "https://m.media-amazon.com/images/I/61DvziZF3yL._AC_UY1000_.jpg"));
        server.createContext("/product/2", new ProductHandler("Sport Hoodie", 59.99, "Lightweight hoodie perfect for workouts and running.", "https://images.sportsdirect.com/images/products/51112203_l.jpg"));
        server.createContext("/product/3", new ProductHandler("Winter Hoodie", 69.99, "Extra warm hoodie for those cold winter days.", "https://m.media-amazon.com/images/I/71T8qI1980L._AC_UY1000_.jpg"));
        
        // Add login and registration pages
        server.createContext("/login", new LoginHandler());
        server.createContext("/register", new RegisterHandler());
        
        // Start the server
        server.setExecutor(null);
        server.start();
        
        System.out.println("Server started on port 8090");
        System.out.println("Visit http://localhost:8090 to see the status page");
    }
    
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Hoodies Store</title>\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                    "    <style>\n" +
                    "        body { font-family: 'Arial', sans-serif; padding-top: 0; }\n" +
                    "        .hero { background-color: #f8f9fa; padding: 60px 0; margin-bottom: 30px; }\n" +
                    "        .card { margin-bottom: 20px; transition: transform 0.3s; height: 100%; }\n" +
                    "        .card:hover { transform: translateY(-5px); box-shadow: 0 10px 20px rgba(0,0,0,0.1); }\n" +
                    "        .status-badge { position: absolute; top: 10px; right: 10px; }\n" +
                    "        .navbar { box-shadow: 0 2px 4px rgba(0,0,0,0.1); }\n" +
                    "        .product-img { height: 200px; object-fit: cover; }\n" +
                    "        #featured { padding: 40px 0; }\n" +
                    "        #about { padding: 40px 0; background-color: #f8f9fa; }\n" +
                    "        .footer { margin-top: 0; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark sticky-top\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <a class=\"navbar-brand\" href=\"#\">Hoodies Store</a>\n" +
                    "            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\">\n" +
                    "                <span class=\"navbar-toggler-icon\"></span>\n" +
                    "            </button>\n" +
                    "            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                    "                <ul class=\"navbar-nav ms-auto\">\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link active\" href=\"#\">Home</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"#featured\">Products</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"#about\">About</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"#status\">Status</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/login\">Login</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/register\">Register</a></li>\n" +
                    "                </ul>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </nav>\n" +
                    "\n" +
                    "    <section class=\"hero text-center\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <h1 class=\"display-4\">Welcome to Hoodies Store</h1>\n" +
                    "            <p class=\"lead\">Your one-stop shop for premium hoodies</p>\n" +
                    "            <div class=\"alert alert-success d-inline-block mt-3\">\n" +
                    "                <strong>Server Status:</strong> <span class=\"badge bg-success\">ONLINE</span>\n" +
                    "            </div>\n" +
                    "            <div class=\"mt-4\">\n" +
                    "                <a href=\"#featured\" class=\"btn btn-primary me-2\">Shop Now</a>\n" +
                    "                <a href=\"/status\" class=\"btn btn-outline-primary\">Check API Status</a>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </section>\n" +
                    "\n" +
                    "    <section id=\"featured\" class=\"py-5\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <h2 class=\"text-center mb-5\">Featured Products</h2>\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-md-4 mb-4\">\n" +
                    "                    <div class=\"card h-100\">\n" +
                    "                        <img src=\"https://m.media-amazon.com/images/I/61DvziZF3yL._AC_UY1000_.jpg\" class=\"card-img-top product-img\" alt=\"Classic Hoodie\">\n" +
                    "                        <div class=\"card-body d-flex flex-column\">\n" +
                    "                            <h5 class=\"card-title\">Classic Hoodie</h5>\n" +
                    "                            <p class=\"card-text\">Our best-selling classic hoodie with premium fabric.</p>\n" +
                    "                            <div class=\"d-flex justify-content-between align-items-center mt-auto\">\n" +
                    "                                <span class=\"badge bg-primary fs-6\">$49.99</span>\n" +
                    "                                <a href=\"/product/1\" class=\"btn btn-sm btn-outline-primary\">View Details</a>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div class=\"col-md-4 mb-4\">\n" +
                    "                    <div class=\"card h-100\">\n" +
                    "                        <img src=\"https://images.sportsdirect.com/images/products/51112203_l.jpg\" class=\"card-img-top product-img\" alt=\"Sport Hoodie\">\n" +
                    "                        <div class=\"card-body d-flex flex-column\">\n" +
                    "                            <h5 class=\"card-title\">Sport Hoodie</h5>\n" +
                    "                            <p class=\"card-text\">Lightweight hoodie perfect for workouts and running.</p>\n" +
                    "                            <div class=\"d-flex justify-content-between align-items-center mt-auto\">\n" +
                    "                                <span class=\"badge bg-primary fs-6\">$59.99</span>\n" +
                    "                                <a href=\"/product/2\" class=\"btn btn-sm btn-outline-primary\">View Details</a>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div class=\"col-md-4 mb-4\">\n" +
                    "                    <div class=\"card h-100\">\n" +
                    "                        <img src=\"https://m.media-amazon.com/images/I/71T8qI1980L._AC_UY1000_.jpg\" class=\"card-img-top product-img\" alt=\"Winter Hoodie\">\n" +
                    "                        <div class=\"card-body d-flex flex-column\">\n" +
                    "                            <h5 class=\"card-title\">Winter Hoodie</h5>\n" +
                    "                            <p class=\"card-text\">Extra warm hoodie for those cold winter days.</p>\n" +
                    "                            <div class=\"d-flex justify-content-between align-items-center mt-auto\">\n" +
                    "                                <span class=\"badge bg-primary fs-6\">$69.99</span>\n" +
                    "                                <a href=\"/product/3\" class=\"btn btn-sm btn-outline-primary\">View Details</a>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div class=\"text-center mt-4\">\n" +
                    "                <button class=\"btn btn-lg btn-success\">View All Products</button>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </section>\n" +
                    "\n" +
                    "    <section id=\"about\" class=\"py-5\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-md-6\">\n" +
                    "                    <h2 class=\"mb-4\">About Hoodies Store</h2>\n" +
                    "                    <p>We are passionate about creating high-quality hoodies that are both comfortable and stylish. Our mission is to provide our customers with the best hoodie experience at affordable prices.</p>\n" +
                    "                    <p>Founded in 2025, Hoodies Store has quickly become a leading provider of premium hoodies for all occasions and seasons.</p>\n" +
                    "                    <p>This is a sample application running on a simple Java HTTP server to demonstrate web functionality.</p>\n" +
                    "                </div>\n" +
                    "                <div class=\"col-md-6\">\n" +
                    "                    <h2 class=\"mb-4\">Server Information</h2>\n" +
                    "                    <div class=\"card\">\n" +
                    "                        <div class=\"card-body\" id=\"status\">\n" +
                    "                            <h5 class=\"card-title\">Live Status</h5>\n" +
                    "                            <ul class=\"list-group\">\n" +
                    "                                <li class=\"list-group-item d-flex justify-content-between align-items-center\">\n" +
                    "                                    Server Type\n" +
                    "                                    <span>Simple Java HTTP Server</span>\n" +
                    "                                </li>\n" +
                    "                                <li class=\"list-group-item d-flex justify-content-between align-items-center\">\n" +
                    "                                    Running on Port\n" +
                    "                                    <span>8090</span>\n" +
                    "                                </li>\n" +
                    "                                <li class=\"list-group-item d-flex justify-content-between align-items-center\">\n" +
                    "                                    Status\n" +
                    "                                    <span class=\"badge bg-success rounded-pill\">Running</span>\n" +
                    "                                </li>\n" +
                    "                                <li class=\"list-group-item d-flex justify-content-between align-items-center\">\n" +
                    "                                    Start Time\n" +
                    "                                    <span>" + java.time.LocalDateTime.now() + "</span>\n" +
                    "                                </li>\n" +
                    "                            </ul>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </section>\n" +
                    "\n" +
                    "    <section class=\"bg-light py-5\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <div class=\"row align-items-center\">\n" +
                    "                <div class=\"col-md-6\">\n" +
                    "                    <h2>Subscribe to Our Newsletter</h2>\n" +
                    "                    <p>Stay updated with the latest products and promotions.</p>\n" +
                    "                </div>\n" +
                    "                <div class=\"col-md-6\">\n" +
                    "                    <div class=\"input-group\">\n" +
                    "                        <input type=\"email\" class=\"form-control\" placeholder=\"Enter your email address\">\n" +
                    "                        <button class=\"btn btn-dark\" type=\"button\">Subscribe</button>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </section>\n" +
                    "\n" +
                    "    <footer class=\"bg-dark text-white py-4\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-md-4 mb-3\">\n" +
                    "                    <h5>Hoodies Store</h5>\n" +
                    "                    <p>Quality hoodies for every occasion.</p>\n" +
                    "                </div>\n" +
                    "                <div class=\"col-md-4 mb-3\">\n" +
                    "                    <h5>Quick Links</h5>\n" +
                    "                    <ul class=\"list-unstyled\">\n" +
                    "                        <li><a href=\"#\" class=\"text-white\">Home</a></li>\n" +
                    "                        <li><a href=\"#featured\" class=\"text-white\">Products</a></li>\n" +
                    "                        <li><a href=\"#about\" class=\"text-white\">About Us</a></li>\n" +
                    "                        <li><a href=\"/status\" class=\"text-white\">API Status</a></li>\n" +
                    "                    </ul>\n" +
                    "                </div>\n" +
                    "                <div class=\"col-md-4 mb-3\">\n" +
                    "                    <h5>Contact Us</h5>\n" +
                    "                    <address class=\"text-white\">\n" +
                    "                        123 Hoodie Lane<br>\n" +
                    "                        Fashion City, FC 12345<br>\n" +
                    "                        Email: contact@hoodiesstore.com\n" +
                    "                    </address>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <hr>\n" +
                    "            <div class=\"text-center\">\n" +
                    "                <p class=\"mb-0\">© 2025 Hoodies Store. All rights reserved.</p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </footer>\n" +
                    "\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    "</body>\n" +
                    "</html>";
            
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class ProductHandler implements HttpHandler {
        private String name;
        private double price;
        private String description;
        private String imageUrl;

        public ProductHandler(String name, double price, String description, String imageUrl) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.imageUrl = imageUrl;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>" + name + " | Hoodies Store</title>\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                    "    <style>\n" +
                    "        body { font-family: 'Arial', sans-serif; padding-top: 0; }\n" +
                    "        .product-img { max-height: 400px; object-fit: contain; }\n" +
                    "        .navbar { box-shadow: 0 2px 4px rgba(0,0,0,0.1); }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark sticky-top\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <a class=\"navbar-brand\" href=\"/\">Hoodies Store</a>\n" +
                    "            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\">\n" +
                    "                <span class=\"navbar-toggler-icon\"></span>\n" +
                    "            </button>\n" +
                    "            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                    "                <ul class=\"navbar-nav ms-auto\">\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/\">Home</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link active\" href=\"/#featured\">Products</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/#about\">About</a></li>\n" +
                    "                </ul>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </nav>\n" +
                    "\n" +
                    "    <div class=\"container py-5\">\n" +
                    "        <nav aria-label=\"breadcrumb\">\n" +
                    "            <ol class=\"breadcrumb\">\n" +
                    "                <li class=\"breadcrumb-item\"><a href=\"/\">Home</a></li>\n" +
                    "                <li class=\"breadcrumb-item\"><a href=\"/#featured\">Products</a></li>\n" +
                    "                <li class=\"breadcrumb-item active\" aria-current=\"page\">" + name + "</li>\n" +
                    "            </ol>\n" +
                    "        </nav>\n" +
                    "\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"col-md-6\">\n" +
                    "                <img src=\"" + imageUrl + "\" class=\"img-fluid product-img\" alt=\"" + name + "\">\n" +
                    "            </div>\n" +
                    "            <div class=\"col-md-6\">\n" +
                    "                <h1>" + name + "</h1>\n" +
                    "                <div class=\"d-flex align-items-center mb-3\">\n" +
                    "                    <div class=\"me-3\">\n" +
                    "                        <span class=\"badge bg-success\">In Stock</span>\n" +
                    "                    </div>\n" +
                    "                    <div>\n" +
                    "                        <span class=\"text-warning\">\n" +
                    "                            <i class=\"bi bi-star-fill\"></i>\n" +
                    "                            <i class=\"bi bi-star-fill\"></i>\n" +
                    "                            <i class=\"bi bi-star-fill\"></i>\n" +
                    "                            <i class=\"bi bi-star-fill\"></i>\n" +
                    "                            <i class=\"bi bi-star-half\"></i>\n" +
                    "                        </span>\n" +
                    "                        <small class=\"text-muted ms-1\">4.5 (128 reviews)</small>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <h2 class=\"text-primary mb-4\">$" + price + "</h2>\n" +
                    "                <p class=\"lead mb-4\">" + description + "</p>\n" +
                    "                \n" +
                    "                <div class=\"mb-4\">\n" +
                    "                    <h5>Select Size:</h5>\n" +
                    "                    <div class=\"btn-group\" role=\"group\">\n" +
                    "                        <input type=\"radio\" class=\"btn-check\" name=\"size\" id=\"size-s\" autocomplete=\"off\">\n" +
                    "                        <label class=\"btn btn-outline-dark\" for=\"size-s\">S</label>\n" +
                    "                        <input type=\"radio\" class=\"btn-check\" name=\"size\" id=\"size-m\" autocomplete=\"off\" checked>\n" +
                    "                        <label class=\"btn btn-outline-dark\" for=\"size-m\">M</label>\n" +
                    "                        <input type=\"radio\" class=\"btn-check\" name=\"size\" id=\"size-l\" autocomplete=\"off\">\n" +
                    "                        <label class=\"btn btn-outline-dark\" for=\"size-l\">L</label>\n" +
                    "                        <input type=\"radio\" class=\"btn-check\" name=\"size\" id=\"size-xl\" autocomplete=\"off\">\n" +
                    "                        <label class=\"btn btn-outline-dark\" for=\"size-xl\">XL</label>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                \n" +
                    "                <div class=\"mb-4\">\n" +
                    "                    <h5>Select Color:</h5>\n" +
                    "                    <div class=\"d-flex\">\n" +
                    "                        <div class=\"form-check me-3\">\n" +
                    "                            <input class=\"form-check-input\" type=\"radio\" name=\"color\" id=\"color-black\" checked>\n" +
                    "                            <label class=\"form-check-label\" for=\"color-black\">Black</label>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"form-check me-3\">\n" +
                    "                            <input class=\"form-check-input\" type=\"radio\" name=\"color\" id=\"color-gray\">\n" +
                    "                            <label class=\"form-check-label\" for=\"color-gray\">Gray</label>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"form-check\">\n" +
                    "                            <input class=\"form-check-input\" type=\"radio\" name=\"color\" id=\"color-blue\">\n" +
                    "                            <label class=\"form-check-label\" for=\"color-blue\">Blue</label>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                \n" +
                    "                <div class=\"d-flex align-items-center mb-4\">\n" +
                    "                    <div class=\"input-group me-3\" style=\"width: 130px;\">\n" +
                    "                        <button class=\"btn btn-outline-secondary\" type=\"button\">-</button>\n" +
                    "                        <input type=\"text\" class=\"form-control text-center\" value=\"1\">\n" +
                    "                        <button class=\"btn btn-outline-secondary\" type=\"button\">+</button>\n" +
                    "                    </div>\n" +
                    "                    <button class=\"btn btn-primary btn-lg\">Add to Cart</button>\n" +
                    "                </div>\n" +
                    "                \n" +
                    "                <div class=\"card mt-4\">\n" +
                    "                    <div class=\"card-body\">\n" +
                    "                        <h5 class=\"card-title\">Product Features:</h5>\n" +
                    "                        <ul class=\"mb-0\">\n" +
                    "                            <li>Premium cotton blend material</li>\n" +
                    "                            <li>Comfortable fit</li>\n" +
                    "                            <li>Machine washable</li>\n" +
                    "                            <li>Available in multiple colors and sizes</li>\n" +
                    "                        </ul>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <footer class=\"bg-dark text-white py-4 mt-5\">\n" +
                    "        <div class=\"container text-center\">\n" +
                    "            <p class=\"mb-0\">© 2025 Hoodies Store. All rights reserved.</p>\n" +
                    "        </div>\n" +
                    "    </footer>\n" +
                    "\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    "</body>\n" +
                    "</html>";
            
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    static class StatusHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "{ \"status\": \"UP\", \"message\": \"The application is running successfully!\", \"timestamp\": \"" + java.time.LocalDateTime.now() + "\" }";
            
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Login - Hoodies Store</title>\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                    "    <style>\n" +
                    "        body { font-family: 'Arial', sans-serif; padding-top: 0; background-color: #f8f9fa; }\n" +
                    "        .navbar { box-shadow: 0 2px 4px rgba(0,0,0,0.1); }\n" +
                    "        .login-container { max-width: 450px; margin: 0 auto; padding: 30px; background-color: white; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 50px; }\n" +
                    "        .login-title { text-align: center; margin-bottom: 30px; color: #333; }\n" +
                    "        .btn-primary { background-color: #007bff; border-color: #007bff; }\n" +
                    "        .btn-primary:hover { background-color: #0069d9; border-color: #0062cc; }\n" +
                    "        .form-control:focus { border-color: #007bff; box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25); }\n" +
                    "        .separator { display: flex; align-items: center; text-align: center; margin: 20px 0; }\n" +
                    "        .separator::before, .separator::after { content: ''; flex: 1; border-bottom: 1px solid #dee2e6; }\n" +
                    "        .separator span { padding: 0 10px; color: #6c757d; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark sticky-top\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <a class=\"navbar-brand\" href=\"/\">Hoodies Store</a>\n" +
                    "            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\">\n" +
                    "                <span class=\"navbar-toggler-icon\"></span>\n" +
                    "            </button>\n" +
                    "            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                    "                <ul class=\"navbar-nav ms-auto\">\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/\">Home</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/#featured\">Products</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/#about\">About</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link active\" href=\"/login\">Login</a></li>\n" +
                    "                </ul>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </nav>\n" +
                    "\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"login-container\">\n" +
                    "            <h2 class=\"login-title\">Login to Your Account</h2>\n" +
                    "            <form>\n" +
                    "                <div class=\"mb-3\">\n" +
                    "                    <label for=\"email\" class=\"form-label\">Email address</label>\n" +
                    "                    <input type=\"email\" class=\"form-control\" id=\"email\" placeholder=\"Enter your email\">\n" +
                    "                </div>\n" +
                    "                <div class=\"mb-3\">\n" +
                    "                    <label for=\"password\" class=\"form-label\">Password</label>\n" +
                    "                    <input type=\"password\" class=\"form-control\" id=\"password\" placeholder=\"Enter your password\">\n" +
                    "                </div>\n" +
                    "                <div class=\"mb-3 form-check\">\n" +
                    "                    <input type=\"checkbox\" class=\"form-check-input\" id=\"rememberMe\">\n" +
                    "                    <label class=\"form-check-label\" for=\"rememberMe\">Remember me</label>\n" +
                    "                </div>\n" +
                    "                <div class=\"d-grid\">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-primary btn-lg\">Login</button>\n" +
                    "                </div>\n" +
                    "                <div class=\"text-center mt-3\">\n" +
                    "                    <a href=\"#\" class=\"text-decoration-none\">Forgot password?</a>\n" +
                    "                </div>\n" +
                    "                <div class=\"separator\">\n" +
                    "                    <span>or</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"text-center\">\n" +
                    "                    <p>Don't have an account? <a href=\"/register\" class=\"text-decoration-none\">Register now</a></p>\n" +
                    "                </div>\n" +
                    "            </form>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <footer class=\"bg-dark text-white py-4 mt-5\">\n" +
                    "        <div class=\"container text-center\">\n" +
                    "            <p class=\"mb-0\">© 2025 Hoodies Store. All rights reserved.</p>\n" +
                    "        </div>\n" +
                    "    </footer>\n" +
                    "\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    "</body>\n" +
                    "</html>";
            
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class RegisterHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Register - Hoodies Store</title>\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                    "    <style>\n" +
                    "        body { font-family: 'Arial', sans-serif; padding-top: 0; background-color: #f8f9fa; }\n" +
                    "        .navbar { box-shadow: 0 2px 4px rgba(0,0,0,0.1); }\n" +
                    "        .register-container { max-width: 500px; margin: 0 auto; padding: 30px; background-color: white; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 50px; }\n" +
                    "        .register-title { text-align: center; margin-bottom: 30px; color: #333; }\n" +
                    "        .btn-primary { background-color: #007bff; border-color: #007bff; }\n" +
                    "        .btn-primary:hover { background-color: #0069d9; border-color: #0062cc; }\n" +
                    "        .form-control:focus { border-color: #007bff; box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25); }\n" +
                    "        .separator { display: flex; align-items: center; text-align: center; margin: 20px 0; }\n" +
                    "        .separator::before, .separator::after { content: ''; flex: 1; border-bottom: 1px solid #dee2e6; }\n" +
                    "        .separator span { padding: 0 10px; color: #6c757d; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark sticky-top\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <a class=\"navbar-brand\" href=\"/\">Hoodies Store</a>\n" +
                    "            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\">\n" +
                    "                <span class=\"navbar-toggler-icon\"></span>\n" +
                    "            </button>\n" +
                    "            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                    "                <ul class=\"navbar-nav ms-auto\">\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/\">Home</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/#featured\">Products</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/#about\">About</a></li>\n" +
                    "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"/login\">Login</a></li>\n" +
                    "                </ul>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </nav>\n" +
                    "\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"register-container\">\n" +
                    "            <h2 class=\"register-title\">Create a New Account</h2>\n" +
                    "            <form>\n" +
                    "                <div class=\"row mb-3\">\n" +
                    "                    <div class=\"col-md-6\">\n" +
                    "                        <label for=\"firstName\" class=\"form-label\">First Name</label>\n" +
                    "                        <input type=\"text\" class=\"form-control\" id=\"firstName\" placeholder=\"Enter your first name\">\n" +
                    "                    </div>\n" +
                    "                    <div class=\"col-md-6\">\n" +
                    "                        <label for=\"lastName\" class=\"form-label\">Last Name</label>\n" +
                    "                        <input type=\"text\" class=\"form-control\" id=\"lastName\" placeholder=\"Enter your last name\">\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div class=\"mb-3\">\n" +
                    "                    <label for=\"email\" class=\"form-label\">Email address</label>\n" +
                    "                    <input type=\"email\" class=\"form-control\" id=\"email\" placeholder=\"Enter your email\">\n" +
                    "                </div>\n" +
                    "                <div class=\"mb-3\">\n" +
                    "                    <label for=\"password\" class=\"form-label\">Password</label>\n" +
                    "                    <input type=\"password\" class=\"form-control\" id=\"password\" placeholder=\"Create a password\">\n" +
                    "                    <small class=\"form-text text-muted\">Password must be at least 8 characters long and include a letter and a number.</small>\n" +
                    "                </div>\n" +
                    "                <div class=\"mb-3\">\n" +
                    "                    <label for=\"confirmPassword\" class=\"form-label\">Confirm Password</label>\n" +
                    "                    <input type=\"password\" class=\"form-control\" id=\"confirmPassword\" placeholder=\"Confirm your password\">\n" +
                    "                </div>\n" +
                    "                <div class=\"mb-3 form-check\">\n" +
                    "                    <input type=\"checkbox\" class=\"form-check-input\" id=\"agreeTerms\">\n" +
                    "                    <label class=\"form-check-label\" for=\"agreeTerms\">I agree to the Terms and Conditions</label>\n" +
                    "                </div>\n" +
                    "                <div class=\"d-grid\">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-primary btn-lg\">Register</button>\n" +
                    "                </div>\n" +
                    "                <div class=\"separator\">\n" +
                    "                    <span>or</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"text-center\">\n" +
                    "                    <p>Already have an account? <a href=\"/login\" class=\"text-decoration-none\">Login here</a></p>\n" +
                    "                </div>\n" +
                    "            </form>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <footer class=\"bg-dark text-white py-4 mt-5\">\n" +
                    "        <div class=\"container text-center\">\n" +
                    "            <p class=\"mb-0\">© 2025 Hoodies Store. All rights reserved.</p>\n" +
                    "        </div>\n" +
                    "    </footer>\n" +
                    "\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    "</body>\n" +
                    "</html>";
            
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
} 