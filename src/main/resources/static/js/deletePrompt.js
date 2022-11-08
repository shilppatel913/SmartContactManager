console.log("hello the delete prompt will be implemented here")

function deleteAlert(cId){
			swal({
		  	title: "Are you sure?",
		  	text: "Once deleted, you will not be able to recover this contact!",
		  	icon: "warning",
		  	buttons: true,
		  	dangerMode: true,
		})
			.then((willDelete) => {
		 	 if (willDelete) {
		  	 	window.location="/user/delete/"+cId
		  } else {
		    swal("Your contact is saved!");
		  }
		});
}