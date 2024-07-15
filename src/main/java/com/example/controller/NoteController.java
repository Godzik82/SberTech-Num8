package com.example.controller;

import com.example.models.Note;
import com.example.services.NotesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequiredArgsConstructor
// @RequestMapping("/notes")
public class NoteController {
    private final NotesService notesService;

    @GetMapping("/notes")
    public String getNotesPage(@RequestParam(name = "title", required = false) String title,
                                @RequestParam(name = "dateTime", required = false) String dateTime,
                                Model model) {
        List<Note> notesList;
        if (dateTime != null && !dateTime.isEmpty()) {
            notesList = notesService.searchNotesByDeadline(dateTime);
        } else {
            notesList = (title != null && !title.isEmpty()) ? notesService.listNotes(title) : notesService.listNotes(null);
        }
        model.addAttribute("notesList", notesList);
        model.addAttribute("note", new Note());
        return "notesList";
    }

    @GetMapping("/archive")
    public String getArchive(@RequestParam(name = "title", required = false) String title,
                                @RequestParam(name = "dateTime", required = false) String dateTime,
                                Model model) {
        List<Note> archivedNotesList;
        if (dateTime != null && !dateTime.isEmpty()) {
            archivedNotesList = notesService.searchNotesByDeadlineArchived(dateTime);
        } else {
            archivedNotesList = (title != null && !title.isEmpty()) ? notesService.listArchivedNotes(title) : notesService.listArchivedNotes(null);
        }
        model.addAttribute("archivedNotesList", archivedNotesList);
        model.addAttribute("note", new Note());
        return "archivedNotesList";
    }

    @GetMapping("/filter")
    public String getNotesPageStatus(Boolean status, Model model) {
        List<Note> notesList = notesService.filterNotesByStatus(status);
        model.addAttribute("notesList", notesList);
        model.addAttribute("note", new Note());
        return "notesList";
    }

    @GetMapping("/note/{id}")
    public String noteInfo(@PathVariable Long id, Model model) {
        model.addAttribute("note", notesService.getNoteById(id));
        return "note-info";
    }

    @PostMapping("/note/create")
    public String createNote(Note note) {
        notesService.saveNote(note);
        return "redirect:/notes";
    }

    @PostMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        notesService.deleteNote(id);
        return "redirect:/notes";
    }

    @PostMapping("/note/change/status/{id}")
    public String changeStatus(@PathVariable Long id) {
        notesService.changeStatusById(id);
        return "redirect:/notes";
    }

    @PostMapping("/note/update")
    public String updateNote(Note updatedNote) {
        notesService.updateNote(updatedNote);
        return "redirect:/notes";
    }

    @PostMapping("/note/archive/{id}")
    public String archiveNote(@PathVariable Long id) {
        notesService.archiveNoteById(id, true);
        return "redirect:/notes";
    }

    @PostMapping("/note/unarchive/{id}")
    public String unarchiveNote(@PathVariable Long id) {
        notesService.archiveNoteById(id, false);
        return "redirect:/archive";
    }

}