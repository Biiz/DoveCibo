<%-- 
    Document   : modificaRistorante
    Created on : 19-set-2016, 10.49.47
    Author     : IO-PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" media="all" />   
        <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.9.1.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="./bootstrap-slider/bootstrap-slider.css" media="all" />
        <script src="./bootstrap-slider/bootstrap-slider.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>DoveCibo</title>
        <style>
            .colonna2{
                background-color: rgba(255, 255, 255, 0.80);
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5), 0 6px 20px 0 rgba(0, 0, 0, 0.7);
                border-radius: 5px;
            }
            body {
                background-image: url("img/img (7)b.jpg");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
                color: #000000;
            }
        </style>

        <script>
            $("#ex2").slider();
                        
            //se il form è vuoto viene settato un valore di default
            function nome_ristoranteFill() {
                var x = document.getElementById("nome_ristorante");
                if(x.value==""){
                    x.value="Nome Ristorante";
                }
            }
            
            function linkFill() {
                var x = document.getElementById("link");
                if(x.value==""){
                    x.value="http://www.example.com";
                }
            }
            
            function txtNazioneFill() {
                var x = document.getElementById("txtNazione");
                if(x.value==""){
                    x.value="Nazione";
                }
            }
            
            function txtCityFill() {
                var x = document.getElementById("txtCity");
                if(x.value==""){
                    x.value="Città";
                }
            }
            
            function txtViaFill() {
                var x = document.getElementById("txtVia");
                if(x.value==""){
                    x.value="Via";
                }
            }
            
            function txtNumeroFill() {
                var x = document.getElementById("txtNumero");
                if(x.value==""){
                    x.value="1";
                }
            }
            
            function latFill() {
                var x = document.getElementById("lat");
                if(x.value==""){
                    x.value="1";
                }
            }
            function lonFill() {
                var x = document.getElementById("lng");
                if(x.value==""){
                    x.value="1";
                }
            }
            
            function descrizioneFill() {
                var x = document.getElementById("descrizione");
                if(x.value==""){
                    x.value="descrizione ecc ecc";
                }
            }
        </script>

    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <form name="addRestaurantForm"  action="ServletStampaTuttoRistorante" method="post">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                    <div class="col-md-6 col-sm-8 col-xs-10 colonna2">
                        <div id="tagline">
                            <h1>Modifica il tuo Ristorante</h1>
                        </div>
                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 1° row: nome e link-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nome Ristorante:</label>          
                                    <input type="text" id="nome_ristorante" onchange="nome_ristoranteFill()" class="form-control" name="nome_ristorante"  pattern=".{3,255}" title="Inserisci il nome!" placeholder="Nome Ristorante" value="Nome Ristorante">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Link al sito ufficiale:</label>          
                                    <input type="url" id="link" onchange="linkFill()" class="form-control" name="link" pattern="https?://.+.{3,255}" title="Aggiungi http://www.example.com" placeholder="http://www.example.com" value="http://www.example.com">
                                </div>
                            </div>
                        </div>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 2° row: nazione e città-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nazione:</label>          
                                    <input type="text" id="txtNazione" onchange="txtNazioneFill()" class="form-control" name="nazione" placeholder="Nazione" pattern=".{3,255}" title="Inserisci la nazione!" value="nazione">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Città:</label>          
                                    <input type="text" id="txtCity" onchange="txtCityFill()" class="form-control" name="city" placeholder="Città" pattern=".{3,255}" title="Inserisci la città!" value="città">
                                </div>
                            </div>
                        </div>
                        
                        <!-- 3° row: indirizzo-->
                        <div class="row">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label>Via:</label>          
                                    <input type="text" id="txtVia" onchange="txtViaFill()" class="form-control" name="via" placeholder="Via" pattern=".{3,255}" title="Inserisci la via" value="via">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Numero civico:</label>          
                                    <input type="number" id="txtNumero" onchange="txtNumeroFill()" class="form-control" name="numero_civico" placeholder="123" pattern=".{1,255}" title="Inserisci il numero civico!" value="7">
                                </div>
                            </div>
                        </div>

                        <!-- 4° row: coordinate geografiche-->
                        <div class="row" >
                            <%@include file="aggiungiRistoranteMap.jsp" %>
                        </div>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 5° row: descrizione ristorante-->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="comment">Descrizione:</label>
                                    <textarea class="form-control"  id="descrizione" onchange="descrizioneFill()" rows="5" name="descrizione" pattern=".{3,32000}" title="Descrivi il ristorante!" value="descrizione"></textarea>
                                </div>
                            </div>
                        </div>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 6° row: tipologia di cucina-->
                        <div class="row">
                            <div class="col-md-12">
                                <h4><b>Tipologie di cucina:</b></h4>
                            </div>
                        </div>
                        <div class="row">    
                            <div class="col-md-5">
                                <div class="funkyradio">
                                    <div class="funkyradio-info">
                                        <input type="checkbox" name="cb1" id="checkbox1" />
                                        <label for="checkbox1">Pizzeria</label>
                                    </div>
                                    <div class="funkyradio-info">
                                        <input type="checkbox" name="cb2" id="checkbox2" />
                                        <label for="checkbox2">Trattoria</label>
                                    </div>
                                    <div class="funkyradio-info">
                                        <input type="checkbox" name="cb3" id="checkbox3"/>
                                        <label for="checkbox3">Ristorante</label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                            <div class="col-md-5">
                                <div class="funkyradio">
                                    <div class="funkyradio-info">
                                        <input type="checkbox" name="cb4" id="checkbox4"/>
                                        <label for="checkbox4">Disco restaurant</label>
                                    </div>
                                    <div class="funkyradio-info">
                                        <input type="checkbox" name="cb5" id="checkbox5"/>
                                        <label for="checkbox5">Straniera</label>
                                    </div>
                                    <div class="funkyradio-info">
                                        <input type="checkbox" name="cb6" id="checkbox6"/>
                                        <label for="checkbox6">Altro</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 7° row: fascia di prezzo-->
                        <div class="row">
                            <div class="col-md-12 text-center">
                                <h4><b>Fascia di prezzo:</b></h4>
                                <div class="row">
                                    <div class="col-md-12">
					<%@include file="aggiungiRistoranteSliderPrice.jsp" %>
                                    </div>
                                </div> <!-- fine row cena -->
                            </div>
                        </div>
                        <br>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 8° row: orario di apertura-->
                        <div class="row">
                            <div class="col-md-12">
                                <h4><b>Orario di apertura:</b></h4>
                                <div class="row">
                                    <div class="col-md-12"> 
                                        <%@include file="aggiungiRistoranteSliderTime.jsp" %>
                                    </div>
                                </div> <!-- fine row cena -->
                            </div>
                        </div>

                        <br>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 9° row: caricamento foto-->
                        <div class="row">
                            <div class="col-md-12">
                                <label class="btn btn-info btn-group-justified btn-file">
                                    <span class="glyphicon glyphicon-upload"></span> Seleziona foto da caricare <input type="file" accept="image/*" style="display: none;">
                                </label> 
                            </div>
                        </div>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 10° row: bottoni-->
                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-success btn-lg pull-right"><span class="glyphicon glyphicon glyphicon-ok"></span> Sign in</button>
                                <button type="reset" class="btn btn-sm btn-danger" onclick="goBack()"><span class="glyphicon glyphicon-backward"></span> Annulla</button>
                                <!-- script per tornare indietro di pagina nel browser-->
                                <script>
                                    function goBack() {
                                        window.history.back();
                                    }
                                </script>
                                <button type="reset" class="btn btn-sm btn-warning"><span class="glyphicon glyphicon-remove"></span> Cancella Campi</button>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                </div><br>
            </div>    
        </form>

    </body>
</html>

</html>
