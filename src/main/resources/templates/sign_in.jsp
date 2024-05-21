<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar Example</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
        <div class="container-fluid mx-3 d-flex">
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
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0 flex-row">
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa-solid fa-user"></i>
                        Sign Up
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa-solid fa-cart-shopping"></i>
                        Cart
                    </a>
                </li>
            </ul>
        </div>
    </nav>
    <div>
        <main style="margin-top: 100px;">
            <div class="container mt-2 mt-md-5 d-flex justify-content-evenly flex-wrap flex-sm-nowrap">
                <div class="col-12 col-sm-5 d-flex align-items-center flex-wrap justify-content-evenly">
                    <img src="/img/logo.jpg" width="25%" >
                    <div class="d-none d-sm-block text-center">
                        <h4 class="mt-4">Your one-stop shop for outdoor comfort and luxurious home essentials</h4>
                    </div>
                </div>
                <div class="col-9 col-sm-6">
                    <div class="container">
                        <div class= "row justify-content-center border p-3 " style="border-radius: 30px;" id="sign_in">
                            <h1 class="text-center">Sign in</h1>
                            <form action="users/login" method="post" class="w-75" id="loginForm">
                                <input type="email" class="form-control mt-5" name="email" placeholder="Email" id="usernameIn">
                                <input type="password" name="password" class="form-control mt-4" placeholder="Password" id="passwordIn">

                                <button type="submit" class="btn-primary mt-3 rounded-pill p-2 ps-3 pe-3">Sign in</button>
                            </form>
                            <p class="mt-5 d-flex justify-content-center">Already doesn't have account?</p>
                            <button onclick="toogle()"  class="btn-primary rounded-pill p-2 ps-3 pe-3 w-50">Sign up</button>
                        </div>

                        <div class="row justify-content-center border p-3 " style="border-radius: 30px; display: none;" id="sign_up">
                            <h1 class="text-center">Sign up</h1>
                            <form action="users/registration" method="post" class="w-75" id="signUpForm">
                                <input class="form-control mt-4" placeholder="Firstname" name="firstname">
                                <input class="form-control mt-4" placeholder="Lastname" name="lastname">
                                <input class="form-control mt-5" type="email" name="email" placeholder="Email" id="username" onchange="checkUsername()">
                                <p class="mb-0" id="usernameChecker"></p>
                                <input type="password" name="password" class="form-control mt-4" placeholder="Password" id="password" onchange="checkPassword()">
                                <p class="mb-0" id="passwordChecker"></p>
                                <input type="text" name="phone" class="form-control mt-4" placeholder="Phone number" id="phone">
                                <button type="submit" class="btn-primary mt-3 rounded-pill p-2 ps-3 pe-3">Sign up</button>
                            </form>
                            <p class="mt-5 d-flex justify-content-center">Already have account?</p>
                            <button onclick="toogle()"  class="btn-primary rounded-pill p-2 ps-3 pe-3 w-50">Sign in</button>
                        </div>

                    </div>
                </div>
            </div>
        </main>
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

    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>

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

    <script src="/scripts/sign_in.js"></script>
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
</html>
