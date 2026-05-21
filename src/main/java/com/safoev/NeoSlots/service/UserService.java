package com.safoev.NeoSlots.service;

import com.safoev.NeoSlots.domain.entity.User;
import com.safoev.NeoSlots.dto.request.UserRequest;
import com.safoev.NeoSlots.dto.response.UserResponse;
import com.safoev.NeoSlots.exception.EntityNotFoundException;
import com.safoev.NeoSlots.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;

    public List<UserResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse findById(UUID id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь", id)));
    }

    @Transactional
    public UserResponse create(UserRequest request) {
        var user = new User();
        user.setPhone(request.phone());
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPasswordHash(request.passwordHash());
        user.setTelegramId(request.telegramId());
        user.setRole(request.role());
        return toResponse(repository.save(user));
    }

    @Transactional
    public UserResponse update(UUID id, UserRequest request) {
        var user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь", id));
        user.setPhone(request.phone());
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPasswordHash(request.passwordHash());
        user.setTelegramId(request.telegramId());
        user.setRole(request.role());
        return toResponse(repository.save(user));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Пользователь", id);
        }
        repository.deleteById(id);
    }

    private UserResponse toResponse(User u) {
        return new UserResponse(
                u.getId(), u.getPhone(), u.getFullName(), u.getEmail(),
                u.getTelegramId(), u.getRole(), u.getIsActive(),
                u.getCreatedAt(), u.getUpdatedAt());
    }
}
