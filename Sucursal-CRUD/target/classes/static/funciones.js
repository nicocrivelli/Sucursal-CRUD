function eliminar(id) {
	Swal.fire({
		title: '¿Esta seguro de eliminar?',
		text: "Esta accion es irreversible",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si, eliminar'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "/eliminar/" + id,
				success (res) {
					console.log(res);
				}
			});
			Swal.fire(
				'Eliminado',
				'Se elimino correctamente',
				'success'
			).then((OK) => {
				if(OK){
					location.href="/listar";
				}
			});
		}
	})
}