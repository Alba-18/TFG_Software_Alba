function deleteArticle(id) {
	console.log(id);
	swal({
		  title: "¿Esta seguro de eliminar esta Noticia?",
		  text: "¡Una vez eliminada no se prodra restablecer!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/article/delete/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("¡Noticia eliminada!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/article/list";
		    	}
		    });
		  } 
		});
}