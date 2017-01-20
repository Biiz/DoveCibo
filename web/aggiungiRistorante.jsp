<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./bootstrap-slider/bootstrap-slider.css" media="all" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>
        <script src="./bootstrap-slider/bootstrap-slider.js"></script>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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

            .funkyradio div {
                clear: both;
                /*margin: 0 50px;*/
                overflow: hidden;
            }
            .funkyradio label {
                /*min-width: 400px;*/
                width: 100%;
                border-radius: 3px;
                border: 1px solid #808080;
                font-weight: normal;
            }
            .funkyradio input[type="radio"]:empty, .funkyradio input[type="checkbox"]:empty {
                display: none;
            }
            .funkyradio input[type="radio"]:empty ~ label, .funkyradio input[type="checkbox"]:empty ~ label {
                position: relative;
                line-height: 2.2em;
                text-indent: 3.25em;

                cursor: pointer;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }
            .funkyradio input[type="radio"]:empty ~ label:before, .funkyradio input[type="checkbox"]:empty ~ label:before {
                position: absolute;
                display: block;
                top: 0;
                bottom: 0;
                left: 0;
                content:'';
                width: 2.5em;
                background: #D1D3D4;
                border-radius: 3px 0 0 3px;
            }
            .funkyradio input[type="radio"]:hover:not(:checked) ~ label:before, .funkyradio input[type="checkbox"]:hover:not(:checked) ~ label:before {
                content:'\2714';
                text-indent: .9em;
                color: #C2C2C2;
            }
            .funkyradio input[type="radio"]:hover:not(:checked) ~ label, .funkyradio input[type="checkbox"]:hover:not(:checked) ~ label {
                color: #888;
            }
            .funkyradio input[type="radio"]:checked ~ label:before, .funkyradio input[type="checkbox"]:checked ~ label:before {
                content:'\2714';
                text-indent: .9em;
                color: #333;
                background-color: #ccc;
            }
            .funkyradio input[type="radio"]:checked ~ label, .funkyradio input[type="checkbox"]:checked ~ label {
                color: #000000;
                background-color: #ccf2ff;
            }
            .funkyradio input[type="radio"]:focus ~ label:before, .funkyradio input[type="checkbox"]:focus ~ label:before {
                box-shadow: 0 0 0 3px #999;
            }
            .funkyradio-info input[type="radio"]:checked ~ label:before, .funkyradio-info input[type="checkbox"]:checked ~ label:before {
                color: #fff;
                background-color: #5bc0de;
                padding-top: 3px;
                padding-bottom: 3px;
            }
        </style>

        <script> $("#ex2").slider(); </script>
    </head>
    
    <body style="padding-top: 70px;">
        <%@ include file="navBar.jsp" %>
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
                                    <input type="text" class="form-control" name="nome_ristorante"  pattern=".{3,255}" title="Inserisci il nome!" placeholder="Nome Ristorante" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Link al sito ufficiale:</label>          
                                    <input type="url" class="form-control" name="link" pattern="https?://.+.{3,255}" title="Aggiungi http://www.example.com" placeholder="http://www.example.com" required>
                                </div>
                            </div>
                        </div>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 2° row: nazione e città-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nazione:</label>          
                                    <input type="text" id="txtNazione" class="form-control" name="nazione" placeholder="Nazione" pattern=".{3,255}" title="Inserisci la nazione!"required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Città:</label>          
                                    <input type="text" id="txtCity" class="form-control" name="city" placeholder="Città" pattern=".{3,255}" title="Inserisci la città!"required>
                                </div>
                            </div>
                        </div>

                        <!-- 3° row: indirizzo-->
                        <div class="row">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label>Via:</label>          
                                    <input type="text" id="txtVia" class="form-control" name="via" placeholder="Via" pattern=".{3,255}" title="Inserisci la via"required>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Civico:</label>          
                                    <input type="number" id="txtNumero" class="form-control" name="numero_civico" placeholder="123" pattern=".{1,255}" title="Inserisci il numero civico!"required>
                                </div>
                            </div>
                        </div>

                        <!-- 4° row: coordinate geografiche-->
                        <div class="row" > <%@include file="aggiungiRistoranteMap.jsp" %> </div>
                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 5° row: descrizione ristorante-->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="comment">Descrizione:</label>
                                    <textarea class="form-control" rows="5" name="descrizione" pattern=".{3,32000}" title="Descrivi il ristorante!" required></textarea>
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
                                    <div class="col-md-12"> <%@include file="aggiungiRistoranteSliderPrice.jsp" %> </div>
                                </div> <!-- fine row cena -->
                            </div>
                        </div>
                        <br>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>                       

                        <!-- 5° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <label for="comment">Orari di apertura Pranzo:</label>
                                <br>

                                <div class="col-md-3">
                                    <label for="sel1"> Da, ore:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="StMh">
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">minuti:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="StMm">
                                            <option>0</option>
                                            <option>15</option>
                                            <option>30</option>
                                            <option>45</option>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">A ore:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="FtMh">
                                            <option>13</option>
                                            <option>14</option>
                                            <option>15</option>
                                            <option>16</option>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">minuti:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="FtMm">
                                            <option>0</option>
                                            <option>15</option>
                                            <option>30</option>
                                            <option>45</option>
                                          </select>
                                    </div>
                                </div>               
                            </div>
                        </div>
                        
                        <!-- 5° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <label for="comment">Orari di apertura Cena</label>
                                <br>

                                <div class="col-md-3">
                                    <label for="sel1"> Da, ore:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="StPh">
                                            <option>17</option>
                                            <option>18</option>
                                            <option>19</option>
                                            <option>20</option>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">minuti:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="StPm">
                                            <option>0</option>
                                            <option>15</option>
                                            <option>30</option>
                                            <option>45</option>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">A ore:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="FtPh">
                                            <option>21</option>
                                            <option>22</option>
                                            <option>23</option>
                                          </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="sel1">minuti:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                                        <select class="form-control" id="sel1" name="FtPm">
                                            <option>0</option>
                                            <option>15</option>
                                            <option>30</option>
                                            <option>45</option>
                                          </select>
                                    </div>
                                </div>                
                            </div>
                        </div>
                        <br>
                        
                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>

                        <!-- 9° row: caricamento foto
                        <div class="row">
                            <div class="col-md-12">
                                <label class="btn btn-info btn-group-justified btn-file">
                                    <span class="glyphicon glyphicon-upload"></span> Seleziona foto da caricare <input type="file" accept="image/*" style="display: none;">
                                </label> 
                            </div>
                        </div>

                        <hr align=”left” size=”1″ width=”300″ style="border-top-color: grey;" noshade>-->

                        <!-- 10° row: bottoni-->
                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-success btn-lg pull-right"><span class="glyphicon glyphicon glyphicon-ok"></span> Conferma</button>
                                <button type="reset" class="btn btn-sm btn-danger" onclick="goBack()"><span class="glyphicon glyphicon-backward"></span> Annulla</button>
                                
                                <!-- script per tornare indietro di pagina nel browser-->
                                <script> function goBack() { window.history.back(); } </script>
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
