$(function () {
    const noteModal = $('#noteModal');

    const error = noteModal.data("error");

    if (!error) return;
    noteModal[0].classList.remove("fade");
    noteModal.modal("show");
    noteModal[0].classList.add("fade");
})


// Edit modals
$(function () {
    const editButton = $('button.btn.edit-button');
    const modal = $("#noteModal");
    const noteForm = $("#note-form");

    editButton.click(async function () {
        modal.modal("show");

        const id = $(this).data("id");
        const note = await fetchDetailNote(id);

        if (note.statusCode === 404) {
            $("#noteModal .modal-body").prepend(
                `<div class='alert alert-danger'>${note.message}</div>`
            )
            modal.find("input, textarea, button#btn-note-submit").attr("disabled", "disabled");
            return;
        }

        $('#note-title').val(note.data.title);
        $('#note-description').val(note.data.description);
        modal.attr("data-id", id);
    });

    modal.on("shown.bs.modal", function (e) {
        const id = modal.attr("data-id");
        if (!Number(id)) return;

        $("button#btn-note-submit").text("Edit changes");

        if (!noteForm) return;
        noteForm.prepend("<input type='hidden' name='_method' value='PATCH'/>")
        noteForm.attr("action", `/note/${id}`);
    });

    modal.on("hidden.bs.modal", function (e) {
        $("#note-form input").first().remove();
    })
})

async function fetchDetailNote(id) {
    return (await fetch(`http://localhost:8080/note/${id}`, {method: "GET"})).json()
};