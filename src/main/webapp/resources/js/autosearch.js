
			  jQuery(function() {
				   $( "#studentSearch" ).autocomplete({
				          select: function(event, ui) {   
				        	  window.location.href="/student/edit?id=" + ui.item.id;
				            },
					      source: function( request, response ) {
					        $.ajax({
					          url: "/getStudent",
					          dataType: "json",
					          data: {
					            studentParam: request.term
					          },
				                success: function( data ) {
				                    response( $.map( data, function( item ) {
				                    	console.log(item.name);
				                        return {
				                            label: item.matNo + " " + item.name + " " + item.surName,
				                            value: item.matNo + " " + item.name,
				                            id: item.id
				                        }
				                    }));
				                }
					        });
					      },
					      minLength: 3,
					      max: 10
				    });

								
				  });