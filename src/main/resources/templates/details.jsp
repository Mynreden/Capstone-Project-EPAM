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
        <main>
            <div class="container-fluid d-md-flex justify-content-around mt-3 p-0">
                <div class="d-flex justify-content-center col-md-6 col-12">
                    <div class="slideshow-container">
                        <div th:each="image : ${product.images}">
                            <div class="mySlides">
                                <img th:src="${image.url}" style="width:100%">
                            </div>
                        </div>
                        <div class="d-flex flex-row flex-nowrap">
                            <a class="prev" onclick="plusSlides(-1)">❮</a>
                            <a class="next" onclick="plusSlides(1)">❯</a>
                        </div>

                    </div>
                </div>
                <div class="d-flex flex-column col-md-4 col-sm-12 px-4 px-md-0">
                    <h2 class="mb-0 mt-3 mt-md-0" th:utext="${product.name}">Name</h2>
                    <p  th:utext="${product.category.name}">Category</p>
                    <h2>
                        KZT <span th:utext="${product.minPrice}">0</span>
                    </h2>
                    <p class="mt-3">
                        <span th:text="${product.description.length() > 300} ? ${product.description.substring(0, 300)} + '...' : ${product.description}"></span>
                        <span th:if="${product.description.length() > 300}" class="read-more-content" th:text="${product.description.substring(300)}"></span>
                        <span th:if="${product.description.length() > 300}" class="read-more-button">Read More</span>
                    </p>
                    <button type="button" class="btn-secondary border-0 mt-3 rounded-pill" style="background-color:  #E9F3EE; padding: 10px;" data-bs-toggle="modal" data-bs-target="#cartModal">
                        Add to Cart
                    </button>

                </div>
            </div>
        </main>
    </div>

    <div class="modal fade" id="cartModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="myForm" action="/carts/addproduct" method="POST">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Add to Cart</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                            <input type="hidden" id="selectedBlock" name="productVariantId" value="">
                            <div th:each="productVariant : ${product.productVariants}">
                                <div class="block" th:onclick="'selectBlock(this, \'' + ${productVariant.id} + '\')'" th:text="${(productVariant.size != null ? 'Size: ' + productVariant.size : '')
                            + (productVariant.size != null && productVariant.color != null ? ', ' : '')
                            + (productVariant.color != null ? 'Color: ' + productVariant.color : '')
                            + (' Price: ' + productVariant.price)}"></div>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
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

    <script>
        let slideIndex = 1;
        showSlides(slideIndex);

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            let i;
            let slides = document.getElementsByClassName("mySlides");
            if (n > slides.length) {slideIndex = 1}
            if (n < 1) {slideIndex = slides.length}
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slides[slideIndex-1].style.display = "block";
        }

        document.addEventListener('DOMContentLoaded', function () {
            var readMoreButtons = document.querySelectorAll('.read-more-button');

            readMoreButtons.forEach(function(button) {
                button.addEventListener('click', function() {
                    var readMoreContent = this.previousElementSibling;
                    if (readMoreContent.style.display === 'none' || readMoreContent.style.display === '') {
                        readMoreContent.style.display = 'inline';
                        this.textContent = 'Read Less';
                    } else {
                        readMoreContent.style.display = 'none';
                        this.textContent = 'Read More';
                    }
                });
            });
        });

        function selectBlock(block, value) {
            // Remove 'selected' class from all blocks
            var blocks = document.querySelectorAll('.block');
            blocks.forEach(function (element) {
                element.classList.remove('selected');
            });

            // Add 'selected' class to the clicked block
            block.classList.add('selected');

            // Set the value of the hidden input field to the selected block's value
            document.getElementById('selectedBlock').value = value;
        }

    </script>

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
