<%@page import="home.Beans.Membre"%>
<%@page import="home.Beans.Emprunt"%>
<%@page import="java.util.List"%>
<%@page import="home.Beans.Media"%>
<%@page import="home.Beans.Categorie"%>
<%@page import="home.controllers.MonPanierServlet"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="fixed">
    <head>

        <!-- Basic -->

        <title>Mediatheque</title>


        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

        <!-- Web Fonts  -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

        <!-- Vendor CSS -->
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
        <link rel="stylesheet" href="assets/vendor/bootstrap-datepicker/css/datepicker3.css" />

        <!-- Specific Page Vendor CSS -->
        <link rel="stylesheet" href="assets/vendor/select2/select2.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-datatables-bs3/assets/css/datatables.css" />

        <!-- Theme CSS -->
        <link rel="stylesheet" href="assets/stylesheets/theme.css" />

        <!-- Skin CSS -->
        <link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

        <!-- Theme Custom CSS -->
        <link rel="stylesheet" href="assets/stylesheets/theme-custom.css">

        <!-- Head Libs -->
        <script src="assets/vendor/modernizr/modernizr.js"></script>

    </head>
    <body>
        <section class="body">
            <c:if test="${sessionScope.sessionMembre==null}">
                <% response.sendRedirect("/Mediatheque/signin.jsp");%>
            </c:if>
            <!-- start: header -->
            <header class="header">
                <div class="logo-container">

                    <div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
                        <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
                    </div>
                </div>

                <!-- start: search & user box -->
                <div class="header-right">



                    <c:if test="${sessionScope.sessionMembre.fonction=='Membre'}" >
                        <span class="separator"></span>

                        <ul class="notifications">
                            <li>
                                <a href="/Mediatheque/MonPanierServlet" class="notification-icon" >
                                    <i class="fa fa-shopping-cart"></i>
                                </a>
                            </li>              
                        </ul>
                    </c:if>
                    <span class="separator"></span>

                    <div id="userbox" class="userbox">
                        <a href="#" data-toggle="dropdown">
                            <figure class="profile-picture">
                                <img src="assets/images/!logged-user.jpg"  class="img-circle" data-lock-picture="assets/images/!logged-user.jpg" />
                            </figure>
                            <div class="profile-info" >
                                <span class="name"> 
                                    ${sessionScope.sessionMembre.nom}
                                </span>
                                <span class="role">${sessionScope.sessionMembre.fonction}</span>
                            </div>

                            <i class="fa custom-caret"></i>
                        </a>

                        <div class="dropdown-menu">
                            <ul class="list-unstyled">
                                <li class="divider"></li>

                                <li>
                                    <a role="menuitem" tabindex="-1" href="/Mediatheque/LogoutServlet"><i class="fa fa-power-off"></i> Se d�connecter</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- end: search & user box -->
            </header>
            <!-- end: header -->

            <div class="inner-wrapper">
                <!-- start: sidebar -->
                <aside id="sidebar-left" class="sidebar-left">

                    <div class="sidebar-header">
                        <div class="sidebar-title">
                            Navigation
                        </div>
                        <div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
                            <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
                        </div>
                    </div>

                    <div class="nano">
                        <div class="nano-content">
                            <nav id="menu" class="nav-main" role="navigation">
                                <ul class="nav nav-main">
                                    <li>
                                        <a href="/Mediatheque/HomeServlet">
                                            <i class="fa fa-home" aria-hidden="true"></i>
                                            <span>Accueil</span>
                                        </a>
                                    </li>
                                    <c:if test="${sessionScope.sessionMembre.fonction=='Membre'}" >

                                        <li>
                                            <a href="/Mediatheque/EmpruntServlet">
                                                <i class="fa fa-cog" aria-hidden="true"></i>
                                                <span>Mes emprunts</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:if test="${sessionScope.sessionMembre.fonction=='Admin'}" >
                                        <li>
                                            <a href="/Mediatheque/RendreMediaServlet">
                                                <i class="fa fa-check" aria-hidden="true"></i>
                                                <span>Rendre les m�dias</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <li class="nav-parent">
                                        <a>
                                            <i class="fa fa-copy" aria-hidden="true"></i>
                                            <span>M�dias</span>
                                        </a>
                                        <ul class="nav nav-children">
                                            <% List<Categorie> listCategorie = (List<Categorie>) request.getAttribute("listcategorie");
                                                for (Categorie categorie : listCategorie) {%>

                                            <li>
                                                <a href="/Mediatheque/MediaServlet?idcategorie=<%= categorie.getIdcategorie()%>">
                                                    <%= categorie.getLibeleCat()%>
                                                </a>
                                            </li>
                                            <%
                                                }
                                            %>	
                                            <c:if test="${sessionScope.sessionMembre.fonction=='Admin'}" >

                                                <li>
                                                    <a href="/Mediatheque/AjouterMediaServlet">
                                                        Ajouter un m�dia
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>

                                    </li>
                                    <c:if test="${sessionScope.sessionMembre.fonction=='Admin'}" >

                                        <li>
                                            <a href="/Mediatheque/MembreServlet">
                                                <i class="fa fa-user" aria-hidden="true"></i>
                                                <span>Membres</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="/Mediatheque/ParamServlet">
                                                <i class="fa fa-cog" aria-hidden="true"></i>
                                                <span>Param�trage</span>
                                            </a>
                                        </li>

                                    </c:if>

                                </ul>
                            </nav>


                            <hr class="separator" />


                        </div>

                    </div>

                </aside>
                <!-- end: sidebar -->

                <section role="main" class="content-body">
                    <header class="page-header">
                        <h2>Acceuil</h2>
                    </header>

                    <!-- start: page -->

                    <div class="col-md-6 col-lg-12 col-xl-6">

                        <c:if test="${sessionScope.sessionMembre.fonction=='Membre'}" >
                            <div class="col-md-12 col-lg-6 col-xl-6">
                                <a href="/Mediatheque/EmpruntServlet">
                                    <section class="panel panel-featured-left panel-featured-primary">
                                        <div class="panel-body">
                                            <div class="widget-summary">
                                                <div class="widget-summary-col widget-summary-col-icon">
                                                    <div class="summary-icon bg-primary">
                                                        <i class="fa fa-cog"></i>
                                                    </div>
                                                </div>
                                                <div class="widget-summary-col">
                                                    <div class="summary">
                                                        </br>
                                                        <div class="info">
                                                            <strong class="amount">Mes emprunts </strong>
                                                        </div>
                                                    </div>
                                                    <div class="summary-footer">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </a>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.sessionMembre.fonction=='Admin'}">
                            <div class="col-md-12 col-lg-6 col-xl-6">
                                <a href="/Mediatheque/RendreMediaServlet">

                                    <section class="panel panel-featured-left panel-featured-primary">
                                        <div class="panel-body">
                                            <div class="widget-summary">
                                                <div class="widget-summary-col widget-summary-col-icon">
                                                    <div class="summary-icon bg-primary">
                                                        <i class="fa fa-check"></i>
                                                    </div>
                                                </div>
                                                <div class="widget-summary-col">
                                                    <div class="summary">
                                                        </br>
                                                        <div class="info">
                                                            <strong class="amount">Rendre les m�dias </strong>
                                                        </div>
                                                    </div>
                                                    <div class="summary-footer">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </a>
                            </div>
                        </c:if>
                        <div class="col-md-12 col-lg-6 col-xl-6">
                            <section class="panel panel-featured-left panel-featured-secondary">
                                <div class="panel-body">
                                    <div class="widget-summary">
                                        <div class="widget-summary-col widget-summary-col-icon">
                                            <div class="summary-icon bg-secondary">
                                                <i class="fa fa-copy"></i>
                                            </div>
                                        </div>
                                        <div class="widget-summary-col">
                                            <div class="summary">
                                                </br>
                                                <div class="info">
                                                    <strong class="amount">M�dias</strong>
                                                </div>
                                            </div>
                                            <div class="summary-footer">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <c:if test="${sessionScope.sessionMembre.fonction=='Membre'}" >
                            <div class="col-md-12 col-lg-6 col-xl-6">
                                <a href="/Mediatheque/MonPanierServlet">

                                    <section class="panel panel-featured-left panel-featured-tertiary">
                                        <div class="panel-body">
                                            <div class="widget-summary">
                                                <div class="widget-summary-col widget-summary-col-icon">
                                                    <div class="summary-icon bg-tertiary">
                                                        <i class="fa fa-shopping-cart"></i>
                                                    </div>
                                                </div>
                                                <div class="widget-summary-col">
                                                    <div class="summary">
                                                        </br>
                                                        <div class="info">
                                                            <strong class="amount">Mon panier</strong>
                                                        </div>
                                                    </div>
                                                    <div class="summary-footer">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </a>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.sessionMembre.fonction=='Admin'}">
                            <div class="col-md-12 col-lg-6 col-xl-6">
                                <a href="/Mediatheque/MembreServlet">

                                    <section class="panel panel-featured-left panel-featured-tertiary">
                                        <div class="panel-body">
                                            <div class="widget-summary">
                                                <div class="widget-summary-col widget-summary-col-icon">
                                                    <div class="summary-icon bg-tertiary">
                                                        <i class="fa fa-user"></i>
                                                    </div>
                                                </div>
                                                <div class="widget-summary-col">
                                                    <div class="summary">
                                                        </br>
                                                        <div class="info">
                                                            <strong class="amount">Membres</strong>
                                                        </div>
                                                    </div>
                                                    <div class="summary-footer">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </a>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.sessionMembre.fonction=='Admin'}">
                            <div class="col-md-12 col-lg-6 col-xl-6">
                                <a href="/Mediatheque/ParamServlet">

                                    <section class="panel panel-featured-left panel-featured-primary">
                                        <div class="panel-body">
                                            <div class="widget-summary">
                                                <div class="widget-summary-col widget-summary-col-icon">
                                                    <div class="summary-icon bg-primary">
                                                        <i class="fa fa-cog"></i>
                                                    </div>
                                                </div>
                                                <div class="widget-summary-col">
                                                    <div class="summary">
                                                        </br>
                                                        <div class="info">
                                                            <strong class="amount">Param�trage</strong>
                                                        </div>
                                                    </div>
                                                    <div class="summary-footer">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </a>
                            </div>
                        </c:if>


                        <!-- end: page -->
                </section>
            </div>


            <!-- Vendor -->
            <script src="assets/vendor/jquery/jquery.js"></script>
            <script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
            <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
            <script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
            <script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
            <script src="assets/vendor/magnific-popup/magnific-popup.js"></script>
            <script src="assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>

            <!-- Specific Page Vendor -->
            <script src="assets/vendor/select2/select2.js"></script>
            <script src="assets/vendor/jquery-datatables/media/js/jquery.dataTables.js"></script>
            <script src="assets/vendor/jquery-datatables/extras/TableTools/js/dataTables.tableTools.min.js"></script>
            <script src="assets/vendor/jquery-datatables-bs3/assets/js/datatables.js"></script>


            <!-- Theme Base, Components and Settings -->
            <script src="assets/javascripts/theme.js"></script>

            <!-- Theme Custom -->
            <script src="assets/javascripts/theme.custom.js"></script>

            <!-- Theme Initialization Files -->
            <script src="assets/javascripts/theme.init.js"></script>

            <!-- Examples -->
            <script src="assets/javascripts/tables/examples.datatables.default.js"></script>

        </section>
    </body>
</html>
