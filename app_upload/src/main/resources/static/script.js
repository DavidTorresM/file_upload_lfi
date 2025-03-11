document.addEventListener("DOMContentLoaded", function() {
    let dropArea = document.getElementById("drop-area");
    let fileInput = document.getElementById("fileElem");
    let uploadBtn = document.getElementById("uploadBtn");
    let status = document.getElementById("status");
    let selectedFile = null;

    dropArea.addEventListener("dragover", (event) => {
        event.preventDefault();
        dropArea.style.background = "#e3e3e3";
    });

    dropArea.addEventListener("dragleave", () => {
        dropArea.style.background = "#f9f9f9";
    });

    dropArea.addEventListener("drop", (event) => {
        event.preventDefault();
        dropArea.style.background = "#f9f9f9";

        selectedFile = event.dataTransfer.files[0];
        status.innerText = "Archivo seleccionado: " + selectedFile.name;
    });

    fileInput.addEventListener("change", () => {
        selectedFile = fileInput.files[0];
        status.innerText = "Archivo seleccionado: " + selectedFile.name;
    });

    uploadBtn.addEventListener("click", () => {
        if (!selectedFile) {
            status.innerText = "Por favor, selecciona un archivo.";
            return;
        }

        let formData = new FormData();
        formData.append("file", selectedFile);

        fetch("/upload", {
            method: "POST",
            body: formData
        })
        .then(response => response.text())
        .then(result => {
            status.innerText = result;
            fileInput.type = "text";  
            fileInput.type = "file";  
            selectedFile = null; 
        })
        .catch(() => {
            status.innerText = "Error al subir el archivo";
        });
    });
});
