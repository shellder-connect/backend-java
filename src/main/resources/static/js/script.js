window.onload = function () {
    const urlParams = new URLSearchParams(window.location.search);
    const erro = urlParams.get("erro");
    if (erro) {
        alert(erro);
    }

    const modaisComTempo = {
        logoutModal: 3000,
        errorModal: 3000
    };

    Object.entries(modaisComTempo).forEach(([id, tempo]) => closeModalById(id, tempo));
};

function closeModalById(id, delay = 4000) {
    const modal = document.getElementById(id);
    if (modal) {
        setTimeout(() => {
            modal.style.display = "none";
        }, delay);
    }
}

function closeModalByElement(element) {
    const modal = element.closest(".modal");
    if (modal) modal.style.display = "none";
}
