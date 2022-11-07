console.log("Hello inside preview Image")

const preview=(event)=>{
	console.log("Image has been changed"+event)
	const imageFiles=event.target.files
	console.log(imageFiles)
	const imageFilesLength=imageFiles.length;
	if(imageFilesLength>0){
		const imageSrc=URL.createObjectURL(imageFiles[0]);
		 const imagePreviewElement = document.querySelector("#preview-selected-image");
		 const imagePreviewContainer=document.querySelector("#image-preview-container");
		 imagePreviewElement.src = imageSrc;
		 imagePreviewContainer.style.display="block";
		 imagePreviewElement.style.display = "block";
		 
	}
}