<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar Example</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
        <ul class="container-fluid mx-3 d-flex mb-0">
            <a class="navbar-brand d-flex no-wrap align-items-center" href="#">
                <img src="/img/logo.jpg" alt="Company Logo" width="60" height="60">
            </a>
            <div class="collapse navbar-collapse justify-content-between">
                <h3>Radosti.kz</h3>

                <ul class="navbar-nav mb-2 mb-lg-0">
                    <li class="nav-item">
                        <form class="d-flex">
                            <input class="form-control me-2 search-bar w-100" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </li>
                </ul>
            </div>
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0 flex-row" th:if="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/sign_in">
                        <i class="fa-solid fa-user"></i>
                        Sign Up
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/sign_in">
                        <i class="fa-solid fa-cart-shopping"></i>
                        Cart
                    </a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0 flex-row" th:if="${user}">
                <li class="nav-item dropdown">
                    <a class="dropdown-toggle nav-link" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fa-solid fa-user"></i>
                        <span th:utext="${user.firstname}"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a class="dropdown-item" href="/orders">
                                <i class="fa-solid fa-cart-shopping"></i>
                                Orders
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="/users/logout">
                                <i class="fa-solid fa-user"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cart">
                        <i class="fa-solid fa-cart-shopping"></i>
                        Cart
                    </a>
                </li>
            </ul>
        </ul>
    </nav>

    <div>
        <div class="d-flex w-100 flex-row justify-content-center flex-wrap" style="background-color: #faeff3;">
            <a href="#" class="mx-4 my-2 text-decoration-none fw-bold" style="color: #e91e63;">
                All Categories
            </a> 
            <a href="#" class="mx-4 my-2 text-black text-decoration-none">
                Canopy
            </a> 
            <a href="#" class="mx-4 my-2 text-black text-decoration-none">
                Wigwam
            </a>
            <a href="#" class="mx-4 my-2 text-black text-decoration-none">
                Bed linen
            </a>
            <a href="#" class="mx-4 my-2 text-black text-decoration-none">
                Pillows
            </a>
        </div>
    </div>
    <div>
        <div>
            <div class="d-flex flex-row" style="background-color: #E9F3EE;">
                <div class="col-6 col-sm-7 col-md-8 p-3 p-md-5 d-flex align-items-center">
                    <div class="d-flex flex-column align-items-center justify-content-center p-md-5">
                        <h3 class="text-center">Canopy</h3>
                        <p class="d-md-none text-center mb-5">Enjoy comfort and protection with Canopy - your serene space companion.</p>
                        <p class="d-none d-md-block text-center mb-5">Experience comfort and protection under the gentle canopy - your reliable companion for creating a serene atmosphere in any space.</p>
                        <button class="btn btn-primary">Read More</button>
                    </div>
                </div>
                <div class="col-6 col-sm-5 col-md-4" >
                    <img src="/img/baldahin.jpg" width="100%">
                </div>
            </div>
        </div>

        <div class="d-flex w-100 flex-row justify-content-center flex-wrap pt-4" style="background-color: #E9F3EE;">
            <a href="#" class="mx-4 my-2 text-decoration-none fw-bold" style="color: #e91e63;">
                Special offer
            </a>
        </div>
        <div style="background-color: #E9F3EE;">
            <div class="container d-flex flex-row justify-content-evenly align-items-center flex-wrap">
                <a href="#">
                    <img src="/img/sale1.jpg" class="rounded-pill" height="170px">
                </a>
                <a href="#">
                    <img src="/img/sale2.png" class="rounded-pill mt-2 mt-md-0" height="170px">
                </a>
            </div>
        </div>
        

        <div class="d-flex w-100 flex-row justify-content-center flex-wrap" style="background-color: #E9F3EE; padding-top: 70px;">
            <a href="#" class="mx-4 my-2 text-decoration-none fw-bold" style="color: #e91e63;">
                Recommended Products
            </a>
        </div>
        <div class="product-section">
            <div class="container d-flex flex-row justify-content-evenly align-items-center flex-wrap">

                <div class="col-6 col-md-4 col-lg-3 mb-5 mb-md-0 px-3" th:each="product : ${products}">
                    <a class="product-item" th:href="'./product/' + ${product.id}">
                        <img th:if="${product.images != null and product.images.size() > 0}" th:src="${product.images[0].url}" alt="Product Image" class="img-fluid product-thumbnail">
                        <h3 class="product-title" th:utext="${product.name}">Name</h3>
                        <strong class="product-price">
                            KZT <span th:utext="${product.minPrice}">0</span>
                        </strong>
                    </a>
                </div>

            </div>
        </div>
        
        
    </div>


   
    <footer class="footer mt-5 pt-4">
        <div class="container">
            <div class="row">
                <!-- Логотип -->
                <div class="col-md-4 d-flex align-items-center justify-content-center">
                    <a class="navbar-brand" href="#">
                        <img src="/img/logo.jpg" alt="Company Logo" width="100" height="100">
                    </a>
                </div>
                <!-- Ссылки на товары -->
                <div class="col-md-4 footer-links flex-row">
                    <a href="#" class="text-center">Canopy</a>
                    <a href="#" class="text-center">Wigwam</a>
                    <a href="#" class="text-center">Bed Linen</a>
                    <a href="#" class="text-center">Pillows</a>
                </div>
                <!-- Социальные сети -->
                <div class="mt-2 mt-md-0 col-md-4 d-flex align-items-center flex-column justify-content-evenly">
                    <a href="https://www.instagram.com" target="_blank" class="text-decoration-none text-black">
                        <i class="fab fa-instagram"></i> Instagram
                    </a>
                    <a href="https://www.whatsapp.com" target="_blank" class="text-decoration-none text-black">
                        <i class="fab fa-whatsapp"></i> WhatsApp
                    </a>
                    <a href="https://www.telegram.org" target="_blank" class="text-decoration-none text-black">
                        <i class="fab fa-telegram"></i> Telegram
                    </a>
                </div>
            </div>
        </div>
    </footer>



    <div th:if="${message}">
        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Error</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" th:utext= "${message}">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://kit.fontawesome.com/6286f41f54.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script></body>

    <div th:if="${message}">
        <script type="text/javascript">
            $(window).on('load', function() {
                $('#staticBackdrop').modal('show');
            });
        </script>
    </div>
</body>
</html>
