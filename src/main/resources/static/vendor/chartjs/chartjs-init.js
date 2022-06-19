(function ($) {
    "use strict"

    //gradient area chart

    const coin_earned = document.getElementById("coin_earned").getContext('2d');
    //generate gradient
    const coin_earnedgradientStroke = coin_earned.createLinearGradient(0, 100, 0, 450);
    coin_earnedgradientStroke.addColorStop(0, "rgba(247, 174, 9, 0.5)");
    coin_earnedgradientStroke.addColorStop(1, "rgba(255, 255, 255, 0.5)");

    coin_earned.height = 100;

    new Chart(coin_earned, {
        type: 'line',
        data: {
            defaultFontFamily: 'Poppins',
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Seo", "Oct", "Nov", "Dec"],
            datasets: [{
                label: "My First dataset",
                data: [25, 20, 60, 41, 66, 45, 80, 20, 60, 41, 66, 45, ],
                borderColor: '#fff',
                borderWidth: "0",
                backgroundColor: coin_earnedgradientStroke,
                pointColor: "#F6B81D",
                pointBorderColor: "#fff",
                pointBackgroundColor: "#F6B81D",
                pointBorderWidth: 3,
                pointRadius: 8,
                pointHoverBackgroundColor: "#F6B81D",
                pointHoverBorderColor: "#fff",
                pointHoverRadius: 7,
                lineTension: 0,
            }]
        },
        options: {
            legend: false,
            scales: {
                yAxes: [{
                    zeroLineWidth: 0,
                    ticks: {
                        beginAtZero: true,
                        max: 100,
                        min: 0,
                        stepSize: 20,
                        padding: 10,
                        fontColor: '#D0D0E6',
                        fontFamily: "'Rubik', sans-serif",
                    },
                    gridLines: {
                        // display: false,
                        color: '#E3E9EF',
                        drawBorder: false,
                        zeroLineColor: '#E3E9EF'
                    },
                }],
                xAxes: [{
                    gridLines: {
                        display: false,
                        drawBorder: false,
                        color: '#E3E9EF',

                    },
                    ticks: {
                        padding: 5,
                        fontColor: '#D0D0E6',
                        fontFamily: "'Rubik', sans-serif",
                    }
                }]
            }
        }
    });

    //gradient area chart


    const offer_completed = document.getElementById("offer_completed").getContext('2d');
    //generate gradient
    const offer_completedgradientStroke = offer_completed.createLinearGradient(0, 100, 0, 450);
    offer_completedgradientStroke.addColorStop(0, "rgba(64, 61, 221, 0.5)");
    offer_completedgradientStroke.addColorStop(1, "rgba(255, 255, 255, 0.5)");

    offer_completed.height = 100;

    new Chart(offer_completed, {
        type: 'line',
        data: {
            defaultFontFamily: 'Poppins',
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Seo", "Oct", "Nov", "Dec"],
            datasets: [{
                label: "My First dataset",
                data: [25, 20, 60, 41, 66, 45, 80, 20, 60, 41, 66, 45, ],
                borderColor: '#fff',
                borderWidth: "0",
                backgroundColor: offer_completedgradientStroke,
                pointColor: "#3D3ADC",
                pointBorderColor: "#fff",
                pointBackgroundColor: "#3D3ADC",
                pointBorderWidth: 3,
                pointRadius: 8,
                pointHoverBackgroundColor: "#3D3ADC",
                pointHoverBorderColor: "#fff",
                pointHoverRadius: 7,
                lineTension: 0,
            }]
        },
        options: {
            legend: false,
            scales: {
                yAxes: [{
                    zeroLineWidth: 0,
                    ticks: {
                        beginAtZero: true,
                        max: 100,
                        min: 0,
                        stepSize: 20,
                        padding: 10,
                        fontColor: '#D0D0E6',
                        fontFamily: "'Rubik', sans-serif",
                    },
                    gridLines: {
                        // display: false,
                        color: '#E3E9EF',
                        drawBorder: false,
                        zeroLineColor: '#E3E9EF'
                    },
                }],
                xAxes: [{
                    gridLines: {
                        display: false,
                        drawBorder: false,
                        color: '#E3E9EF',

                    },
                    ticks: {
                        padding: 5,
                        fontColor: '#D0D0E6',
                        fontFamily: "'Rubik', sans-serif",
                    }
                }]
            }
        }
    });


    const refferal_link = document.getElementById("refferal_link").getContext('2d');
    //generate gradient
    const refferal_linkgradientStroke = refferal_link.createLinearGradient(0, 100, 0, 450);
    refferal_linkgradientStroke.addColorStop(0, "rgba(38, 126, 245, 0.5)");
    refferal_linkgradientStroke.addColorStop(1, "rgba(255, 255, 255, 0.5)");

    refferal_link.height = 100;

    new Chart(refferal_link, {
        type: 'line',
        data: {
            defaultFontFamily: 'Poppins',
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Seo", "Oct", "Nov", "Dec"],
            datasets: [{
                label: "My First dataset",
                data: [25, 20, 60, 41, 66, 45, 80, 20, 60, 41, 66, 45, ],
                borderColor: '#fff',
                borderWidth: "0",
                backgroundColor: refferal_linkgradientStroke,
                pointColor: "#257BF6",
                pointBorderColor: "#fff",
                pointBackgroundColor: "#257BF6",
                pointBorderWidth: 3,
                pointRadius: 8,
                pointHoverBackgroundColor: "#257BF6",
                pointHoverBorderColor: "#fff",
                pointHoverRadius: 7,
                lineTension: 0,
            }]
        },
        options: {
            legend: false,
            scales: {
                yAxes: [{
                    zeroLineWidth: 0,
                    ticks: {
                        beginAtZero: true,
                        max: 100,
                        min: 0,
                        stepSize: 20,
                        padding: 10,
                        fontColor: '#D0D0E6',
                        fontFamily: "'Rubik', sans-serif",
                    },
                    gridLines: {
                        // display: false,
                        color: '#E3E9EF',
                        drawBorder: false,
                        zeroLineColor: '#E3E9EF'
                    },
                }],
                xAxes: [{
                    gridLines: {
                        display: false,
                        drawBorder: false,
                        color: '#E3E9EF',

                    },
                    ticks: {
                        padding: 5,
                        fontColor: '#D0D0E6',
                        fontFamily: "'Rubik', sans-serif",
                    }
                }]
            }
        }
    });

    const order_placed = document.getElementById("order_placed").getContext('2d');
    //generate gradient
    const order_placedgradientStroke = order_placed.createLinearGradient(0, 100, 0, 450);
    order_placedgradientStroke.addColorStop(0, "rgba(113, 243, 190, 0.5)");
    order_placedgradientStroke.addColorStop(1, "rgba(255, 255, 255, 0.5)");

    order_placed.height = 100;

    new Chart(order_placed, {
        type: 'line',
        data: {
            defaultFontFamily: 'Poppins',
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Seo", "Oct", "Nov", "Dec"],
            datasets: [{
                label: "My First dataset",
                data: [25, 20, 60, 41, 66, 45, 80, 20, 60, 41, 66, 45, ],
                borderColor: '#fff',
                borderWidth: "0",
                backgroundColor: order_placedgradientStroke,
                pointColor: "#4EE2A7",
                pointBorderColor: "#fff",
                pointBackgroundColor: "#4EE2A7",
                pointBorderWidth: 3,
                pointRadius: 8,
                pointHoverBackgroundColor: "#4EE2A7",
                pointHoverBorderColor: "#fff",
                pointHoverRadius: 7,
                lineTension: 0,
            }]
        },
        options: {
            legend: false,
            scales: {
                yAxes: [{
                    zeroLineWidth: 0,
                    ticks: {
                        beginAtZero: true,
                        max: 100,
                        min: 0,
                        stepSize: 20,
                        padding: 10,
                        fontColor: '#D0D0E6',
                        fontFamily: "'Rubik', sans-serif",
                    },
                    gridLines: {
                        // display: false,
                        color: '#E3E9EF',
                        drawBorder: false,
                        zeroLineColor: '#E3E9EF'
                    },
                }],
                xAxes: [{
                    gridLines: {
                        display: false,
                        drawBorder: false,
                        color: '#E3E9EF',

                    },
                    ticks: {
                        padding: 5,
                        fontColor: '#D0D0E6',
                        fontFamily: "'Rubik', sans-serif",
                    }
                }]
            }
        }
    });


    // /*======== 16. ANALYTICS - ACTIVITY CHART ========*/
    // var activity = document.getElementById("activity");
    // if (activity !== null) {
    //     var activityData = [{
    //             first: [0, 65, 52, 115, 98, 165, 125],
    //             second: [40, 105, 92, 155, 138, 205, 165]
    //         },
    //         {
    //             first: [0, 65, 77, 33, 49, 100, 100],
    //             second: [20, 85, 97, 53, 69, 120, 120]
    //         },
    //         {
    //             first: [0, 40, 77, 55, 33, 116, 50],
    //             second: [30, 70, 107, 85, 73, 146, 80, ]
    //         },
    //         {
    //             first: [0, 44, 22, 77, 33, 151, 99],
    //             second: [60, 32, 120, 55, 19, 134, 88]
    //         }
    //     ];

    //     activity.height = 100;

    //     var config = {
    //         type: "line",
    //         data: {
    //             labels: [
    //                 "4 Jan",
    //                 "5 Jan",
    //                 "6 Jan",
    //                 "7 Jan",
    //                 "8 Jan",
    //                 "9 Jan",
    //                 "10 Jan"
    //             ],
    //             datasets: [{
    //                     label: "Active",
    //                     backgroundColor: "rgba(93, 120, 255, 0.9)",
    //                     borderColor: "transparent",
    //                     data: activityData[0].first,
    //                     lineTension: 0,
    //                     pointRadius: 0,
    //                     borderWidth: 2,
    //                 },
    //                 {
    //                     label: "Inactive",
    //                     backgroundColor: 'rgba(240, 243, 255, 1)',
    //                     borderColor: "transparent",
    //                     data: activityData[0].second,
    //                     lineTension: 0,
    //                     // borderDash: [10, 5],
    //                     borderWidth: 1,
    //                     pointRadius: 0,
    //                 }
    //             ]
    //         },
    //         options: {
    //             responsive: true,
    //             maintainAspectRatio: false,
    //             legend: {
    //                 display: false
    //             },
    //             scales: {
    //                 xAxes: [{
    //                     gridLines: {
    //                         display: false,
    //                         drawBorder: true
    //                     },
    //                     ticks: {
    //                         fontColor: "#8a909d",
    //                     },
    //                 }],
    //                 yAxes: [{
    //                     gridLines: {
    //                         fontColor: "#8a909d",
    //                         fontFamily: "Nunito, sans-serif",
    //                         display: true,
    //                         color: "#f9f9f9",
    //                         zeroLineColor: "#f9f9f9"
    //                     },
    //                     ticks: {
    //                         stepSize: 50,
    //                         fontColor: "#8a909d",
    //                         fontFamily: "Nunito, sans-serif"
    //                     }
    //                 }]
    //             },
    //             tooltips: {
    //                 mode: "index",
    //                 intersect: false,
    //                 titleFontColor: "#888",
    //                 bodyFontColor: "#555",
    //                 titleFontSize: 12,
    //                 bodyFontSize: 15,
    //                 backgroundColor: "rgba(256,256,256,0.95)",
    //                 displayColors: true,
    //                 xPadding: 10,
    //                 yPadding: 7,
    //                 borderColor: "rgba(220, 220, 220, 0.9)",
    //                 borderWidth: 2,
    //                 caretSize: 6,
    //                 caretPadding: 5
    //             }
    //         }
    //     };

    //     var ctx = document.getElementById("activity").getContext("2d");
    //     var myLine = new Chart(ctx, config);

    //     var items = document.querySelectorAll("#user-activity .nav-tabs .nav-item");
    //     items.forEach(function (item, index) {
    //         item.addEventListener("click", function () {
    //             config.data.datasets[0].data = activityData[index].first;
    //             config.data.datasets[1].data = activityData[index].second;
    //             myLine.update();
    //         });
    //     });
    // }






})(jQuery);