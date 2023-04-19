<%@ page import="model.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Usuari" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>All Posts</title>
    <link rel="icon" type="image/x-icon" href="../assets/favicon.ico"/>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet"
          type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
          rel="stylesheet" type="text/css"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/styles.css" rel="stylesheet"/>

    <%
        ArrayList<Post> postArrayList = (ArrayList<Post>) request.getAttribute("listPost");
    %>

</head>
<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="../index.jsp">WIAM Blog TCP</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <li class="nav-item" name="logOut"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../index.jsp">Salir</a>
                </li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="user.jsp">Mis Posts</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="edit.jsp">Edita tus Posts</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Page Header-->
<%
    for (Post p : postArrayList) {
%>
<header class="masthead" style="background-image: url('../assets/img/post-bg.jpg')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">

                <div class="post-heading">
                    <h1> <p name="titulo"> <%=p.getTitle()%></p></h1>
                    <p> Posteado por: <p name="usuario"> <%=p.getUsuari().getUsuari()%></p>
                    <form action="home.do" method="post">
                    <span class="meta">
                        <p>Fecha: <p name="date"> <%=p.getDate()%></p>
                        <button type="submit" name="like">
                            <img src="https://cdn-icons-png.flaticon.com/512/214/214309.png"/>
                        </button>
                        <p>Likes: <p name="likes"> <%=p.getLikes()%></p>
                            </span>
                    </form>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Post Content-->
<article class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <p name="mensaje"> <%=p.getMessage()%></p>
                <input class="form-control form-control-sm" type="file" name="image">
                <img class="cover center" src="data:image/png;base64,<%=p.getImage()%>" height="400">
                <img class="cover center" src="data:image/png;base64,<%=p.getImage()%>" height="400">
                <p>&middot;Image by :</p>
                <p name="image"> <%=p.getImage()%></p>

            </div>
        </div>
    </div>
</article>

<!-- Footer-->
<footer class="border-top">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <ul class="list-inline text-center">
                    <li class="list-inline-item">
                        <a href="mailto:<%=p.getUsuari().getLinkedin()%>"/>
                        <span class="fa-stack fa-lg">
                                        <i class="fas fa-circle fa-stack-2x"></i>
                                        <i class="fab fa-linkdin fa-stack-1x fa-inverse"></i>
                                    </span>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="mailto:<%=p.getUsuari().getGitlab()%>"/>
                        <span class="fa-stack fa-lg">
                                        <i class="fas fa-circle fa-stack-2x"></i>
                                        <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                                    </span>
                        </a>
                    </li>

                </ul>
                <div class="small text-center text-muted fst-italic">Copyright &copy;TCP 2023</div>
            </div>
        </div>
    </div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="../js/scripts.js"></script>
<%}%>
</body>
</html>
