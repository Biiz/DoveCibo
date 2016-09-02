<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <%@ include file="navBar.html" %>
        <title>DoveCibo</title>

        <style>
            body {
                background-image: url("img/img (7)b.jpg");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }
            .colonna2{
                background-color: rgba(255, 255, 255, 0.75);
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5), 0 6px 20px 0 rgba(0, 0, 0, 0.7);
                border-radius: 5px;
            }
        </style>
    </head>
    <body>

        <!-- notifiche per ristoratore -->
        <div class="modal-dialog modal-lg" >
            <div class="modal-content colonna2">
                <div class="modal-body">

                    <p style="color: black; font-size: 28px"><b>Notifiche per ristoratore</b></p>
                    <!-- effettiva tabella delle notifiche -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th><p style="color: black; font-size: 20px"><b>Nickname</b></p></th>
                                <th><p style="color: black; font-size: 20px"><b>Operazione compiuta</b></p></th>
                                <th><p style="color: black; font-size: 20px"><b>Gestione</b></p></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="info">
                                <td>Johny</td>
                                <td>Ha caricato una foto</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rispondi alla recensione</button> <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                            <tr class="active">
                                <td>Johny</td>
                                <td>Ha scritto una recensione</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rispondi alla recensione</button> <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                            <tr class="active">
                                <td>Johny</td>
                                <td>Ha scritto una recensione</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rispondi alla recensione</button> <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                            <tr class="active">
                                <td>Johny</td>
                                <td>Ha scritto una recensione</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rispondi alla recensione</button> <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                            <tr class="info">
                                <td>Johny</td>
                                <td>Ha caricato una foto</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rispondi alla recensione</button> <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>   
        </div>

        <!-- notifiche per amministratore -->
        <div class="modal-dialog modal-lg" >
            <div class="modal-content colonna2">
                <div class="modal-body">

                    <p style="color: black; font-size: 28px"><b>Notifiche per amministratore</b></p>
                    <!-- effettiva tabella delle notifiche -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th><p style="color: black; font-size: 20px"><b>Nickname</b></p></th>
                                <th><p style="color: black; font-size: 20px"><b>Operazione compiuta</b></p></th>
                                <th><p style="color: black; font-size: 20px"><b>Gestione</b></p></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="info">
                                <td>Johny</td>
                                <td>Ha caricato una foto</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Ok</button>
                                    <button style="align-items: left" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> No</button>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Convalida recensione</button>
                                    <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                            <tr class="active">
                                <td>Johny</td>
                                <td>Ha scritto un commento</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Ok</button>
                                    <button style="align-items: left" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> No</button>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Convalida recensione</button>
                                    <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                            <tr class="info">
                                <td>Johny</td>
                                <td>Ha caricato una foto</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Ok</button>
                                    <button style="align-items: left" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> No</button>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Convalida recensione</button>
                                    <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                            <tr class="warning">
                                <td>Johny</td>
                                <td>Vuole rimuovere una foto</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Ok</button>
                                    <button style="align-items: left" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> No</button>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Convalida recensione</button>
                                    <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                            <tr class="info">
                                <td>Johny</td>
                                <td>Ha scritto un commento</td>
                                <td>
                                    <button style="align-items: left" type="button" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Ok</button>
                                    <button style="align-items: left" type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> No</button>
                                    <button style="align-items: left" type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Convalida recensione</button>
                                    <button style="align-items: left" type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rimuozione foto</button> 
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>   
        </div>

    </body>
</html>
