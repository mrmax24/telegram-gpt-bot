package app.telegramgptbot.adminpanel.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}