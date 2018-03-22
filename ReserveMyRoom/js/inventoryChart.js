var dataVisualization = {

 /*returns visualization data*/
 getBarChartData: function (jsonInputData) {

  var bar, inputData = [], data = new google.visualization.DataTable();

  data.addColumn('string', 'HotelName-Room');

  data.addColumn('number', 'RoomCount');

  data.addColumn({
    type: 'string',
    role: 'tooltip',
    'p': {
     'html': true
    }
   });

  $.each(jsonInputData, function (i, obj) {

   bar = "RoomCount : " + obj.roomCount + "";

   inputData.push([obj.hotelName + "-" + obj.roomName, obj.roomCount, dataVisualization.returnTooltip(bar)]);
  });

  data.addRows(inputData);

  return data;
 },
 getBarChartOptions: function (inputdata) {
	 
  var data = dataVisualization.getBarChartData(inputdata);
  var options = {
   animation: {
    duration: 2000,
    easing: 'out'
   },
   hAxis: {
	title:'Room Count',
    baselineColor: '#ccc'
   },
   vAxis: {
	title:'Hotel Name - Room Name',
    baselineColor: '#ccc',
    gridlineColor: '#fff'
   },

   isStacked: false,
   height: data.getNumberOfRows() * 30 + 80 + 200,
   width:800,
   chartArea: {
	 left:470,
	 height: data.getNumberOfRows() * 30 + 80 + 100,
	 width:330
   },
   backgroundColor: '#fff',
   colors: ["#87CEFF"],
   fontName: 'roboto',
   fontSize: 13,
   bar: { groupWidth: '90%' },
   legend: {
    position: 'bottom',
    alignment: 'end',
    textStyle: {
     color: '#b3b8bc',
     fontName: 'roboto',
     fontSize: 13
    }
   },
   tooltip: {
    isHtml: true,
    showColorCode: true,
    isStacked: false
   }
  };
  return options;
 },
 drawBarChart: function (inputdata) {

  var barOptions = dataVisualization.getBarChartOptions(inputdata),

   data = dataVisualization.getBarChartData(inputdata),

   barChart = new google.visualization.BarChart(document.getElementById('hotelBarChart'));

  barChart.draw(data, barOptions);
  /*To redraw upon window resize*/
  $(window).resize(function () {

   barChart.draw(data, barOptions);
  });
 },
 /*For displaying tooltip*/
 returnTooltip: function (point) {

  return "<div style='height:20px;width:150px;font:11px,roboto;padding:5px 5px 5px 5px;border-radius:3px;'>" +
   "<span style='color:#87CEFF;font:11px,roboto;padding-right:20px;'>" + point + "</span></div>";
 },
 /*Makes call to servlet and downloads data */
 getData: function () {

  $.ajax({

    url: "ChartServlet",

    dataType: "JSON",

    success: function (data) {

     dataVisualization.drawBarChart(data);
    }
   });
 }
};

google.load("visualization", "1", {
  packages: ["corechart"]
 });

$(document).ready(function () {

 dataVisualization.getData();
});