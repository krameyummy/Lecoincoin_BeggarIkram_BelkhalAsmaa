<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>


    <asset:stylesheet src="application.css"/>
    <g:layoutHead/></head>

<body>
<div class="col-lg-9 col-12">
    <div class="menu-area">
        <!-- Main Menu -->
        <nav class="navbar navbar-expand-lg">
            <div class="navbar-collapse">
                <div class="nav-inner">
                    <ul class="nav main-menu menu navbar-nav">
                        <li class="logo"><a href="/login"><g:img file="Logo1.png"/></a></li>
                        <a>  </a>
                        <a>  </a>
                        <a>  </a>
                        <a>  </a>
                            <div class="search-container">
                                <g:form controller="annonce" action="index">
                                    <g:textField type="text" placeholder="Search.." name="motCle" value="${params.motCle}"/>
                                    <button type="submit"><i class="fa fa-search"></i></button>
                                </g:form>
                            </div>
                        <a>  </a>
                        <a>  </a>
                        <a>  </a>
                        <a>  </a>
                        <a>  </a>
                        <a>  </a>
                            <li><a href="/annonce/create"><i class="fas fa-plus"></i> Create ads</a></li>
                            <li><a href="/annonce"><i class="fa fa-file-text"> Show ads </i></a></li>
                            <li class="dropdown">
                                <a class="dropbtn">
                                    <sec:ifLoggedIn>
                                        <i class="fa fa-user" style="font-size:18px" >
                                        <sec:loggedInUserInfo field='username'/> </i>
                                    </sec:ifLoggedIn>
                                    <sec:ifNotLoggedIn>
                                         Se connecter <span class="caret"></span>
                                    </sec:ifNotLoggedIn>
                                </a>
                                <div class="dropdown-content">
                                    <a href="/login"><i class="fa fa-sign-in" style="font-size:18px"></i> Login</a>
                                    <a href="/logout"><i class="fa fa-sign-out" style="font-size:18px"></i> Logout</a>
                                </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>
<g:layoutBody/>
</body>
</html>
