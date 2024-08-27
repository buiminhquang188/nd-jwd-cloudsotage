$(function () {
    const noteModal = $('#credentialModal');

    const error = noteModal.data("error");

    if (!error) return;
    noteModal[0].classList.remove("fade");
    noteModal.modal("show");
    noteModal[0].classList.add("fade");
})

$(function () {
    const editCredentialButton = $('button.btn.btn-edit-credential');
    const credentialModal = $("#credentialModal");
    const credentialNoteForm = $("#credential-form");

    editCredentialButton.click(async function () {
        credentialModal.modal("show");

        const id = $(this).data("id");
        const credential = await fetchDetail(id);
        console.log({id, credential})

        if (credential.statusCode === 404) {
            $("#credentialModal .modal-body").prepend(
                `<div class='alert alert-danger'>${credential.message}</div>`
            )
            credentialModal.find("input, textarea, button#btn-note-submit").attr("disabled", "disabled");
            return;
        }

        $("#credential-url").val(credential.data.url)
        $("#credential-username").val(credential.data.username)
        $("#credential-password").val(credential.data.password)
        credentialModal.attr("data-id", id);
    });

    // modal.on("shown.bs.modal", function (e) {
    //     const id = modal.attr("data-id");
    //     if (!Number(id)) return;
    //
    //     $("button#btn-note-submit").text("Edit changes");
    //
    //     if (!noteForm) return;
    //     noteForm.prepend("<input type='hidden' name='_method' value='PATCH'/>")
    //     noteForm.attr("action", `/note/${id}`);
    // });
    //
    // modal.on("hidden.bs.modal", function (e) {
    //     $("#note-form input").first().remove();
    // })
})

async function fetchDetail(id) {
    return (await fetch(`http://localhost:8080/credential/${id}`, {method: "GET"})).json()
};