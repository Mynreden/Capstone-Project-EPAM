<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar Example</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./css/index.css">
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
        <div class="container-fluid mx-3 d-flex">
            <a class="navbar-brand d-flex no-wrap align-items-center" href="#">
                <img src="../../../../../../../../sultan/CapstoneProjectHTML/img/logo.jpg" alt="Company Logo" width="60" height="60">
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
        <main>
		<!-- Start Hero Section -->
			<div class="hero">
				<div class="container">
					<div class="row justify-content-between">
						<div class="col-lg-5">
							<div class="intro-excerpt">
								<h1>Cart</h1>
							</div>
						</div>
						<div class="col-lg-7">
							
						</div>
					</div>
				</div>
			</div>
		<!-- End Hero Section -->

		

			<div class="untree_co-section before-footer-section">
				<div class="container">
				<div class="row mb-5">
					<form class="col-md-12" method="post">
					<div class="site-blocks-table">
						<table class="table">
						<thead>
							<tr>
							<th class="product-thumbnail">Image</th>
							<th class="product-name">Product</th>
							<th class="product-price">Price</th>
							<th class="product-quantity">Quantity</th>
							<th class="product-total">Total</th>
							<th class="product-remove">Remove</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<td class="product-thumbnail">
								<img src="../../../../../../../../sultan/CapstoneProjectHTML/img/baldahin.jpg" alt="Image" class="img-fluid" style="max-width: 100px; max-height: 100px;">
							</td>
							<td class="product-name">
								<h2 class="h5 text-black">Canopy</h2>
							</td>
							<td>KZT 36.000</td>
							<td>
								<div class="input-group mb-3 d-flex align-items-center quantity-container" style="max-width: 120px;">
								<div class="input-group-prepend">
									<button class="btn btn-outline-black decrease" type="button">&minus;</button>
								</div>
								<input type="text" class="form-control text-center quantity-amount" value="1" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1">
								<div class="input-group-append">
									<button class="btn btn-outline-black increase" type="button">&plus;</button>
								</div>
								</div>
			
							</td>
							<td>KZT 36.000</td>
							<td><a href="#" class="btn btn-black btn-sm">X</a></td>
							</tr>
			
							<tr>
							<td class="product-thumbnail">
								<img src="../../../../../../../../sultan/CapstoneProjectHTML/img/pillow.jpg" alt="Image" class="img-fluid" style="max-width: 100px; max-height: 100px;">
							</td>
							<td class="product-name">
								<h2 class="h5 text-black">Pillow</h2>
							</td>
							<td>KZT 10.000</td>
							<td>
								<div class="input-group mb-3 d-flex align-items-center quantity-container" style="max-width: 120px;">
								<div class="input-group-prepend">
									<button class="btn btn-outline-black decrease" type="button">&minus;</button>
								</div>
								<input type="text" class="form-control text-center quantity-amount" value="1" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1">
								<div class="input-group-append">
									<button class="btn btn-outline-black increase" type="button">&plus;</button>
								</div>
								</div>
			
							</td>
							<td>KZT 10.000</td>
							<td><a href="#" class="btn btn-black btn-sm">X</a></td>
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
								<button class="btn btn-black btn-sm btn-block">Update Cart</button>
							</div>
							<div class="col-md-6">
								<button class="btn btn-outline-black btn-sm btn-block">Continue Shopping</button>
							</div>
						</div>
					</div>
					<div class="col-md-6 pl-5">
					<div class="row justify-content-end">
						<div class="col-md-7">
						<div class="row">
							<div class="col-md-12 text-right border-bottom mb-5">
							<h3 class="text-black h4 text-uppercase">Cart Totals</h3>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-md-6">
							<span class="text-black">Subtotal</span>
							</div>
							<div class="col-md-6 text-right">
							<strong class="text-black">$230.00</strong>
							</div>
						</div>
						<div class="row mb-5">
							<div class="col-md-6">
							<span class="text-black">Total</span>
							</div>
							<div class="col-md-6 text-right">
							<strong class="text-black">$230.00</strong>
							</div>
						</div>
			
						<div class="row">
							<div class="col-md-12">
							<button class="btn btn-black btn-lg py-3 btn-block" onclick="window.location='checkout.html'">Proceed To Checkout</button>
							</div>
						</div>
						</div>
					</div>
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
                        <img src="../img/logo.jpg" alt="Company Logo" width="100" height="100">
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


    <script src="https://kit.fontawesome.com/6286f41f54.js" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
