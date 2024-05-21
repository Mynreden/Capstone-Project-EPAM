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
        <ul class="container-fluid mx-3 d-flex">
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
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0 flex-row"  th:if="${user}">
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa-solid fa-user"></i>
                        <span th:utext="${user.firstname}"></span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/users/logout">
                        <i class="fa-solid fa-user"></i>
                        logout
                    </a>
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
                                <img th:src="${image}" style="width:100%">
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
                        <span th:text="${product.description.length()}"></span>
                        <span th:text="${product.description.length() > 300} ? ${product.description.substring(0, 300)} + '...' : ${product.description}"></span>
                        <span th:if="${product.description.length() > 300}" class="read-more-content" th:text="${product.description.substring(300)}"></span>
                        <span th:if="${product.description.length() > 300}" class="read-more-button">Read More</span>
                    </p>
                    <form th:action="@{'/order/addproduct/' + ${product.id}}" method="post">
                        <button type="submit" class="btn-primary border-0 rounded-pill" style="background-color:#E9F3EE; padding: 10px;">Buy now</button>
                    </form>
                    <form th:action="@{'/order/addproduct/' + ${product.id}}" method="post">
                        <button type="submit"class="btn-secondary border-0 mt-3 rounded-pill" style="background-color:  #E9F3EE; padding: 10px;">Add to Cart</button>
                    </form>

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
    </script>

    <script src="https://kit.fontawesome.com/6286f41f54.js" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
