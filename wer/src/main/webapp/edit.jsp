<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Blog M06"/>
    <meta name="author" content="tcp.id"/>
    <title>Blog TCP M06 UF4</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet"
          type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
          rel="stylesheet" type="text/css"/>
    <link href="css/styles.css" rel="stylesheet"/>


</head>
<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <form  action="index.do" method="get">
            <button class="navbar-toggler" type="submit">WIAM BLOG</button>
        </form>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <form class="site-heading" action="index.do" method="get">
                    <li class="nav-item">
                        <button class="subheading" type="submit">Log Out</button>
                    </li>
                </form>
                <form action="home.do" method="get">
                    <li class="nav-item">
                        <button class="subheading" type="submit">Home</button>
                    </li>
                </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
<header class="masthead" style="background-image: url('assets/img/about-bg.jpg')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="site-heading">
                    <h1>Edita tus Posts In</h1>
                    <h2 class="subheading">Crea, Modifica y Elimina</h2>
                </div>
            </div>
        </div>
    </div>
</header>






<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">

            <form action="edit.do" method="post">
                <div class="form-outline form-white mb-4">
                    <input type="text" name="title" class="form-control form-control-lg"/>
                    <label class="form-label">Titulo</label>
                </div>
                <div class="form-outline form-white mb-4">
                    <input type="text" name="message" class="form-control form-control-lg"/>
                    <label class="form-label">Mensaje</label>
                </div>
                <div class="form-outline form-white mb-4">
                    <input type="file" name="filePost" accept="image/png, image/jpeg"/>
                    <label class="form-label">Imagen</label>
                </div>
                <div class="container position-relative px-4 px-lg-5">
                    <div class="row gx-4 gx-lg-5 justify-content-center">
                        <button class="nav-link px-lg-3 py-3 py-lg-4" type="submit">Post</button>
                    </div>
                </div>
            </form>

            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                <a href="http://www.gitlab.com" class="text-white"><i class="fab fa-github-f fa-lg"></i></a>
            </div>
        </div>
    </div>
</div>

<!-- Footer-->
<footer class="border-top">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <ul class="list-inline text-center">
                    <li class="list-inline-item">
                        <a href="git@github.com:ToniCarpa/servlet.git">
                                    <span class="fa-stack fa-lg">
                                        <i class="fas fa-circle fa-stack-2x"></i>
                                        <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                                    </span>
                        </a>
                    </li>
                </ul>
                <div class="small text-center text-muted fst-italic">Copyright &copy; TCP 2023</div>
            </div>
        </div>
    </div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
