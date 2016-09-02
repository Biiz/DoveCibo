<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" media="all" />   
        <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.9.1.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="./bootstrap-slider/bootstrap-slider.css" media="all" />
        <script src="./bootstrap-slider/bootstrap-slider.js"></script>
    </head>
    <body>

        <table>

            <tr>

                <% String gw[] = {"lun", "mar", "mer", "gio", "ven", "sab", "dom"};

                    for (int i = 0; i < 7; i++) {%>
                <th>

                    <div style="margin: 5px">

                        <b><%=gw[i]%></b>
                        <br/>
                        <input id="StM<%=i%>" name="StM<%=i%>" style="margin: 10px; border-width: 0px; width: 40px" value="7:00" />
                        <br/>
                        <input id="slideMattina<%=i%>" type="text" 
                               class="span2" value="" data-slider-min="7" 
                               data-slider-max="49" data-slider-step="1" data-slider-value="[0,40]"
                               data-slider-ticks="[0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40]" 
                               data-slider-ticks-snap-bounds="30" data-slider-orientation="vertical"
                               data-slider-ticks-labels='["7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"]'/>
                        <br/>
                        <input id="FtM<%=i%>" name="FtM<%=i%>" style="margin: 10px; border-width: 0px; width: 40px" value="17:00"/>
                        <br/>
                        <input id="m-enabled<%=i%>" type="checkbox" checked="checked" value="aperto"/> Aperto
                        <br/>
                        <input id="m-look<%=i%>" type="checkbox" checked="checked" value="look"/> Look

                        <p>---</p>
                        <br/>
                        <input id="StP<%=i%>" name="StP<%=i%>" style="margin: 10px; border-width: 0px; width: 40px" value="17:00"/>
                        <br/>
                        <input id="slidePomeriggio<%=i%>" type="text" 
                               class="span2" value="" data-slider-min="7" 
                               data-slider-max="49" data-slider-step="1" data-slider-value="[0,40]"
                               data-slider-ticks="[0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40]" 
                               data-slider-ticks-snap-bounds="30" data-slider-orientation="vertical"
                               data-slider-ticks-labels='["17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00", "10:00", "2:00", "3:00"]'/>
                        <br/>
                        <input id="FtP<%=i%>" name="FtP<%=i%>" style="margin: 10px; border-width: 0px; width: 40px" value="3:00"/>
                        <br/>
                        <input id="p-enabled<%=i%>" type="checkbox" checked="checked" value="aperto"/> Aperto      
                        <br/>
                        <input id="p-look<%=i%>" type="checkbox" checked="checked" value="look"/> Look

                    </div>

                    <script type="text/javascript">
                        // With JQuery

                        var fm<%=i%> = function () {
                            var ms = (sMat<%=i%>.getValue()[0] % 4) * 15;
                            var hs = ((sMat<%=i%>.getValue()[0] - ms / 15) / 4) + 7;
                            $("#StM<%=i%>").val(hs + ":" + ms);

                            var mf = (sMat<%=i%>.getValue()[1] % 4) * 15;
                            var hf = ((sMat<%=i%>.getValue()[1] - mf / 15) / 4) + 7;
                            $("#FtM<%=i%>").val(hf + ":" + mf);

                            if (document.getElementById("m-look<%=i%>").checked) {
                        <% for (int j = 0; j < 7; j++) {%>
                                if (document.getElementById("m-look<%=j%>").checked && document.getElementById("m-enabled<%=j%>").checked) {
                                    sMat<%=j%>.setValue(sMat<%=i%>.getValue());
                                    $("#StM<%=j%>").val(hs + ":" + ms);
                                    $("#FtM<%=j%>").val(hf + ":" + mf);
                                }
                        <% }%>
                            }
                        };

                        var sMat<%=i%> = $("#slideMattina<%=i%>").slider({
                            tooltip: 'hide',
                            ticks: [0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40],
                            ticks_labels: ["7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"],
                            ticks_snap_bounds: 30
                        })
                                .on('slide', fm<%=i%>)
                                .data('slider');

                        $("#m-enabled<%=i%>").click(function () {
                            if (this.checked) {
                                $("#slideMattina<%=i%>").slider("enable");
                                fp<%=i%>();
                            } else {
                                $("#slideMattina<%=i%>").slider("disable");
                                $("#StM<%=i%>").val("close");
                                $("#FtM<%=i%>").val("close");
                            }
                        });

                        var fp<%=i%> = function () {
                            var ms = (sPom<%=i%>.getValue()[0] % 4) * 15;
                            var hs = ((sPom<%=i%>.getValue()[0] - ms / 15) / 4) + 17;
                            if (hs > 24)
                                hs = hs - 24;
                            $("#StP<%=i%>").val(hs + ":" + ms);

                            var mf = (sPom<%=i%>.getValue()[1] % 4) * 15;
                            var hf = ((sPom<%=i%>.getValue()[1] - mf / 15) / 4) + 17;
                            if (hf > 24)
                                hf = hf - 24;
                            $("#FtP<%=i%>").val(hf + ":" + mf);

                            if (document.getElementById("p-look<%=i%>").checked) {
                        <% for (int j = 0; j < 7; j++) {%>
                                if (document.getElementById("p-look<%=j%>").checked && document.getElementById("p-enabled<%=j%>").checked) {
                                    sPom<%=j%>.setValue(sPom<%=i%>.getValue());
                                    $("#StP<%=j%>").val(hs + ":" + ms);
                                    $("#FtP<%=j%>").val(hf + ":" + mf);
                                }
                        <% }%>
                            }
                        };

                        var sPom<%=i%> = $("#slidePomeriggio<%=i%>").slider({
                            tooltip: 'hide',
                            ticks: [0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40],
                            ticks_labels: ["17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00", "1:00", "2:00", "3:00"],
                            ticks_snap_bounds: 30
                        })
                                .on('slide', fp<%=i%>)
                                .data('slider');

                        $("#p-enabled<%=i%>").click(function () {
                            if (this.checked) {
                                $("#slidePomeriggio<%=i%>").slider("enable");
                                fp<%=i%>();
                            } else {
                                $("#slidePomeriggio<%=i%>").slider("disable");
                                $("#StP<%=i%>").val("close");
                                $("#FtP<%=i%>").val("close");
                            }
                        });


                    </script>
                <th/>
                <% }%>

            <tr/>
        <table/>

        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

    </body>
 </html>