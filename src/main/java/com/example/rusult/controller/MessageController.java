package com.example.rusult.controller;

import com.example.rusult.common.ExceptionType;
import com.example.rusult.exception.CustomException;
import com.example.rusult.pojo.Message;
import com.example.rusult.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public ResponseEntity<List<Message>> list() {
        List<Message> list = this.messageService.findAll();
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/message")
    public ResponseEntity<Message> create(Message message) {
//        Message msg = this.messageService.save(message);
////        if (message == null ||message.getText()==null||message.getText().isEmpty()){
////            throw new CustomException(ExceptionType.USER_INPUT_ERROR);
////        }
//        return ResponseEntity.ok(msg);
        if (message == null || message.getText() == null || message.getText().isEmpty()) {
            throw new CustomException(ExceptionType.USER_INPUT_ERROR);
        }
        try {
            Message msg = this.messageService.updateText(message);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ExceptionType.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @PutMapping("/message")
    public ResponseEntity<Message> modify(Message message) {
        Message msg = this.messageService.update(message);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping("/message/text")
    public ResponseEntity<Message> patch(Message message) {
//        Message msg = this.messageService.updateText(message);
//        return ResponseEntity.ok(msg);
        if (message == null || message.getText() == null || message.getText().isEmpty()) {
            throw new CustomException(ExceptionType.USER_INPUT_ERROR);
        }
        try {
            Message msg = this.messageService.updateText(message);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ExceptionType.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> get(@PathVariable Long id) {
        Message msg = this.messageService.findOne(id);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        this.messageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
