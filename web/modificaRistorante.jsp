<%-- 
    Document   : modificaRistorante
    Created on : 19-set-2016, 10.49.47
    Author     : IO-PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="navBar.html" %>
        <title>DoveCibo</title>

        <style>
            .btn-like {
                padding: 14px 24px;
                border: 0 none;
                font-weight: 700;
                letter-spacing: 1px;
                text-transform: uppercase;
            }

            .btn-like:focus, .btn-like:active:focus, .btn-like.active:focus {
                outline: 0 none;
            }

            .btn-default {
                background: rgba(255, 255, 255, 0);
                color: none;
            }

            .btn-default:hover, .btn-default:focus, .btn-default:active, .btn-default.active, .open > .dropdown-toggle.btn-default {
                color: #0099ff;
                background-color: rgba(255, 255, 255, 0);
            }

            .btn-default:active, .btn-default.active {
                background: rgba(255, 255, 255, 0);
                box-shadow: white;
            }

            #myCarousel img {
                min-width: 600px;
                max-width: 600px;
                height: auto;
                max-height: 400px;
                margin: auto;
            }

            body {
                background-image: url("img/img (7)b.jpg");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }
            .colonna2{
                background-color: rgba(255, 255, 255, 0.80);
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5), 0 6px 20px 0 rgba(0, 0, 0, 0.7);
                border-radius: 5px;
            }

            .rating {
                float:left;
                border:none;
            }
            .rating:not(:checked) > input {
                position:fixed;
                top:-9999px;
                clip:rect(0, 0, 0, 0);


            }
            .rating:not(:checked) > label {
                float:right;
                width:1em;
                padding:0 .1em;
                overflow:hidden;
                white-space:nowrap;
                cursor:pointer;
                font-size:200%;
                line-height:1.2;
                color:#4e4e4e;

            }
            .rating:not(:checked) > label:before {
                content:'★ ';

            }
            .rating > input:checked ~ label {
                color: #f70;

            }
            .rating:not(:checked) > label:hover, .rating:not(:checked) > label:hover ~ label {
                color: gold;

            }
            .rating > input:checked + label:hover, .rating > input:checked + label:hover ~ label, .rating > input:checked ~ label:hover, .rating > input:checked ~ label:hover ~ label, .rating > label:hover ~ input:checked ~ label {
                color: #ea0;
            }
            .rating > label:active {
                position:relative;
            }

        </style>
    </head>
    <body>

        <div class="modal-dialog modal-lg">
            <div class="modal-content colonna2" >
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
                                    <input type="text" class="form-control" name="NomeRistorante" placeholder="Nome Ristorante">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form" action="ServletLogin" method="post">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <div class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></div>
                                                <input type="text" class="form-control" name="Indirizzo" placeholder="Indirizzo">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
                                                <input type="number" class="form-control" name="Fascia di Prezzo" placeholder="Fascia di Prezzo">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <div class="input-group-addon"><span class="glyphicon glyphicon-star"></span></div>
                                                <input type="number" class="form-control" name="Valutazione" placeholder="Valutazione">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <div class="input-group-addon"><span class="glyphicon glyphicon-link"></span></div>
                                                <input type="text" class="form-control" name="Link sito" placeholder="Link sito">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <div class="input-group-addon"><span class="glyphicon glyphicon-cutlery"></span></div>
                                                <input type="text" class="form-control" name="Cucina" placeholder="Cucina">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <div class="input-group-addon"><span class="glyphicon glyphicon-qrcode"></span></div>
                                                <input type="text" class="form-control" name="QRcode" placeholder="QRcode">
                                            </div>
                                        </div>

                                        <button type="reset" class="btn btn-sm btn-warning">Resetta campi</button>
                                        <button type="submit" class="btn btn-success pull-right">Conferma</button> 
                                    </form>  

                                </div>
                            </div>
                            
                            
                        </div>
                        <div class="col-md-8">

                            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                    <li data-target="#myCarousel" data-slide-to="1"></li>
                                    <li data-target="#myCarousel" data-slide-to="2"></li>
                                    <li data-target="#myCarousel" data-slide-to="3"></li>
                                    <li data-target="#myCarousel" data-slide-to="4"></li>
                                    <li data-target="#myCarousel" data-slide-to="5"></li>
                                    <li data-target="#myCarousel" data-slide-to="6"></li>

                                </ol>

                                <!-- Wrapper for slides -->
                                <div class="carousel-inner" role="listbox">
                                    <div class="item active">
                                        <img src="img/img (1).jpeg" alt="">
                                    </div>

                                    <div class="item">
                                        <img src="img/img (1).jpg" alt="">
                                    </div>

                                    <div class="item">
                                        <img src="img/img (2).jpg" alt="">
                                    </div>
                                    <div class="item">
                                        <img src="img/img (3).jpg" alt="">
                                    </div>
                                    <div class="item">
                                        <img src="img/img (4).jpg" alt="">
                                    </div>
                                    <div class="item">
                                        <img src="img/img (5).jpg" alt="">
                                    </div>
                                    <div class="item">
                                        <img src="img/img (6).jpg" alt="">
                                    </div>
                                </div>

                                <!-- Left and right controls -->
                                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>                        
                    </div>

                    <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="comment">Descrizione:</label>
                                <textarea class="form-control" rows="5" name="descrizione" pattern=".{3,32000}" title="Descrivi il ristorante!" required></textarea>
                            </div>
                        </div>
                    </div>


                </div>
            </div>   
        </div>




        <div class="modal-dialog modal-lg" >
            <div class="modal-content colonna2">
                <div class="modal-body">
                    <div class="row">

                        <div class="col-md-12 text-center">
                            <label class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-picture"></span> Carica foto<input type="file" accept="image/*" style="display: none;"></label>

                            <a href="#diversi" class="btn btn-info btn-lg" data-toggle="collapse" ><span class="glyphicon glyphicon-comment"></span> Scrivi una recensione</a>

                            <button type="button" class="btn btn-danger btn-lg"><span class="glyphicon glyphicon-flag" aria-hidden="true"></span> Reclama</button> 
                        </div> 
                    </div>
                    <div id="diversi" class="collapse">
                        <div class="row">
                            <div class="col-md-12">    

                                <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>


                                <div class="row">
                                    <div class="col-md-12">
                                        <div id="tagline">
                                            <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Inserisci commento</b></p>
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">

                                            <textarea class="form-control" rows="5" id="comment"></textarea>
                                        </div>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div id="tagline">
                                            <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Valutazione</b></p>
                                        </div>

                                        <fieldset class="rating">
                                            <input type="radio" id="star5" name="rating" value="5" />
                                            <label for="star5"> 5 stars</label>
                                            <input type="radio" id="star4" name="rating" value="4" />
                                            <label for="star4"> 4 stars</label>
                                            <input type="radio" id="star3" name="rating" checked="checked" value="3" />
                                            <label for="star3"> 3 stars</label>
                                            <input type="radio" id="star2" name="rating" value="2" />
                                            <label for="star2"> 2 stars</label>
                                            <input type="radio" id="star1" name="rating" value="1" />
                                            <label for="star1"> 1 star</label>
                                        </fieldset>

                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <button style="align-items: left" type="button" class="btn btn-success pull-right"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Ok</button> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>                     
            </div>
        </div>   
    </div>











    <div class="modal-dialog modal-lg" >
        <div class="modal-content colonna2">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-9">
                        <div id="tagline">
                            <p style="color: black; font-size: 25px"><b>Autore commento</b></p>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <button style="" type="button" class="btn-like btn-default">
                            <span style="font-size: 25px;" class="glyphicon glyphicon-thumbs-up" aria-hidden="true">123</span>
                        </button>

                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p style="color: #333333; font-size: 20px; margin-bottom: 0px; border-bottom: 0px;">sahdsakjhdakjsdh kjsahdakjshdasj khdaskjdh askd haskdh askjdhasjkdhadjkshadjh ajdhsa jksha kjashaskjhd asjkhdaskj hajkh asjd asjkh  ask askjh d hd ashd hha jah dakjdh akd jks</p>  

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <a href="#risposta" class="btn btn-info pull-right" data-toggle="collapse" ><span class="glyphicon glyphicon-edit"></span> Rispondi</a>
                    </div> 
                </div>


                <div id="risposta" class="collapse">
                    <div class="row">
                        <div class="col-md-12">    

                            <div class="row">
                                <div class="col-md-12">
                                    <div id="tagline">
                                        <p style="color: black; font-size: 20px; padding-top: 15px;"><b>Risposta:</b></p>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">

                                        <textarea class="form-control" rows="5" id="comment"></textarea>
                                    </div>
                                </div>

                            </div>


                            <div class="row">
                                <div class="col-md-12">
                                    <button style="align-items: left" type="button" class="btn btn-success pull-right"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Ok</button> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>   
    </div>

</body>
</html>
