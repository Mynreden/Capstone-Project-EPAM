<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar Example</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="./css/index.css">
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
    <main>
        <form method="post" action="/orders/create" id="orderForm" onsubmit="return validateForm()">
            <div class="hero mb-4">
                <div class="container">
                    <div class="row justify-content-between">
                        <div class="col-lg-5">
                            <div class="intro-excerpt">
                                <h1>You Order:</h1>
                            </div>
                        </div>
                        <div class="col-lg-7 mt-3">
                            <div class="row">
                                <h1 class="mb-3">Please enter your address:</h1>

                                <div class="col-md-3 mb-3">
                                    <input type="text" class="form-control" name="city" placeholder="City" required>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <input type="text" class="form-control" name="street" placeholder="Street" required>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <input type="text" class="form-control" name="house" placeholder="House" required>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <input type="text" class="form-control" name="apartment" placeholder="Apartment" required>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Hero Section -->

            <div class="untree_co-section before-footer-section">
                <div class="container">
                    <div class="row mb-5">
                        <form class="col-md-12">
                            <div class="site-blocks-table">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th class="product-thumbnail">Image</th>
                                        <th class="product-name">Product</th>
                                        <th class="product-quantity">Quantity</th>
                                        <th class="product-total">Total</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr th:each="item : ${cart.cartItems}">
                                        <td class="product-thumbnail">
                                            <img th:src="@{${item.productVariant.product.images[0].url}}" alt="Image" class="img-fluid" style="max-width: 100px; max-height: 100px;">
                                        </td>
                                        <td class="product-name">
                                            <h2 class="h5 text-black" th:text="${item.productVariant.product.name}"></h2>
                                            <p class="text-black" th:text="${(item.productVariant.size != null ? 'Size: ' + item.productVariant.size : '') +
                                                (item.productVariant.size != null && item.productVariant.color != null ? ', ' : '') +
                                                (item.productVariant.color != null ? 'Color: ' + item.productVariant.color : '')}">
                                            </p>
                                        </td>
                                        <td th:text="'KZT ' + ${item.productVariant.price}"></td>
                                        <td>
                                            <div class="input-group mb-3 d-flex align-items-center quantity-container" style="max-width: 120px;">
                                                <input type="text" class="form-control text-center quantity-amount" th:value="${item.quantity}" placeholder="" aria-label="Quantity" aria-describedby="button-addon1">
                                            </div>
                                        </td>
                                        <td th:text="'KZT ' + ${item.productVariant.price * item.quantity}"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="row mb-5">
                                <div class="col-md-6 mb-3 mb-md-0">
                                    <button class="btn btn-black btn-sm btn-block" onclick="window.location='/cart'">Update Cart</button>
                                </div>
                                <div class="col-md-6">
                                    <button class="btn btn-outline-black btn-sm btn-block" onclick="window.location='/'">Continue Shopping</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 pl-5">
                            <div class="row justify-content-end">
                                <div class="col-md-7">
                                    <div class="row">
                                        <div class="col-md-12 text-right border-bottom mb-5">
                                            <h3 class="text-black h4 text-uppercase">Total</h3>
                                        </div>
                                    </div>
                                    <div class="row mb-5">
                                        <div class="text-right">
                                            <strong class="text-black" th:text="${cart.totalPrice} + ' KZT'"></strong>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <button type="submit" class="btn btn-primary btn-block">Create Order</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

    </main>
</div>


<footer class="footer mt-5 pt-4">
    <div class="container">
        <div class="row">
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<div th:if="${message}">
    <script type="text/javascript">
        $(window).on('load', function() {
            $('#staticBackdrop').modal('show');
        });
    </script>
</div>
</body>
</html>
