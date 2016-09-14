<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <%@ include file="navBar.html" %>

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
        </script>

    </head>
    <body>
        <form name="addRestaurantForm"  action="ServletStampaTuttoRistorante" method="post">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                    <div class="col-md-6 col-sm-8 col-xs-10 colonna2">
                        <div id="tagline">
                            <h1>Aggiungi un Ristorante</h1>
                        </div>
                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 1° row: nome e link-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nome Ristorante:</label>          
                                    <input type="text" class="form-control" name="nome_ristorante"  pattern=".{3,255}" title="Inserisci il nome!" placeholder="Nome Ristorante">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Link al sito ufficiale:</label>          
                                    <input type="url" class="form-control" name="link" pattern="https?://.+.{3,255}" title="Aggiungi http://www.example.com" placeholder="link">
                                </div>
                            </div>
                        </div>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 2° row: nazione e città-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nazione:</label>          
                                    <input type="text" id="txtNazione" class="form-control" name="nazione" placeholder="Nazione" pattern=".{3,255}" title="Inserisci la nazione!">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Città:</label>          
                                    <input type="text" id="txtCity" class="form-control" name="city" placeholder="Città" pattern=".{3,255}" title="Inserisci la città!">
                                </div>
                            </div>
                        </div>

                        <!-- 3° row: indirizzo-->
                        <div class="row">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label>Via:</label>          
                                    <input type="text" id="txtVia" class="form-control" name="via" placeholder="Via" pattern=".{3,255}" title="Inserisci la via">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Numero civico:</label>          
                                    <input type="text" id="txtNumero" class="form-control" name="numero_civico" placeholder="123" pattern=".{1,255}" title="Inserisci il numero civico!">
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
                                    <textarea class="form-control" rows="5" name="descrizione" pattern=".{3,32000}" title="Inserisci il numero civico!"></textarea>
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

                            <div class="col-md-12">
                                <h4><b>Fascia di prezzo:</b></h4>

                                <div class="row">
                                    <div class="col-md-12 text-center"> 


                                        <input id="price" name="price" type="text" 
                                               class="span2" value="" data-slider-min="0" 
                                               data-slider-max="100" data-slider-step="5" 
                                               data-slider-value="[20,80]"
                                               data-slider-ticks="[0, 20, 40, 60, 80, 100]" 
                                               data-slider-ticks-snap-bounds="30" 
                                               data-slider-ticks-labels='["€0", "€20", "€40", "€60", "€80", "€100"]'/>  

                                        <script>
                                            $("#price").slider({
                                                ticks: [0, 20, 40, 60, 80, 100],
                                                ticks_labels: ["€0", "€20", "€40", "€60", "€80", "€100"],
                                                ticks_snap_bounds: 30
                                            });
                                        </script>

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
