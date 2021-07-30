package com.wagnerrmorais.springdata.service;

import com.wagnerrmorais.springdata.orm.UnitOfWork;
import com.wagnerrmorais.springdata.repository.UnitOfWorkRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UnitOfWorkService {

    private Boolean system = true;
    private final UnitOfWorkRepository unitOfWorkRepository;

    public UnitOfWorkService(UnitOfWorkRepository unitOfWorkRepository) {
        this.unitOfWorkRepository = unitOfWorkRepository;
    }

    public void init(Scanner scanner) {
        while (system) {
            System.out.println("Which UnitOfWork action would you like to execute?");
            System.out.println("0 - Exit");
            System.out.println("1 - Save");
            System.out.println("2 - Update");
            System.out.println("3 - View all");
            System.out.println("4 - Delete");

            int action = scanner.nextInt();

            switchAction(scanner, action);
        }
    }

    public void save(Scanner scanner) {
        System.out.println("Description: ");
        String description = scanner.next();

        System.out.println("Address: ");
        String address = scanner.next();

        UnitOfWork unidadeTrabalho = new UnitOfWork();
        unidadeTrabalho.setDescription(description);
        unidadeTrabalho.setAddress(address);

        unitOfWorkRepository.save(unidadeTrabalho);
        System.out.println("Salvo");
    }

    public void update(Scanner scanner) {
        System.out.println("Id: ");
        Integer id = scanner.nextInt();

        System.out.println("Description: ");
        String name = scanner.next();

        System.out.println("Address: ");
        String address = scanner.next();

        UnitOfWork uow = new UnitOfWork();
        uow.setId(id);
        uow.setDescription(name);
        uow.setAddress(address);

        unitOfWorkRepository.save(uow);
        System.out.println("Done!");
    }

    public void findAll() {
        Iterable<UnitOfWork> unitiesOfWork = unitOfWorkRepository.findAll();
        unitiesOfWork.forEach(System.out::println);
        system = false;
    }

    private void deleteById(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        unitOfWorkRepository.deleteById(id);
        System.out.println("Deleted!");
    }

    private void switchAction(Scanner scanner, int action) {
        switch (action) {
            case 1:
                save(scanner);
                break;
            case 2:
                update(scanner);
                break;
            case 3:
                findAll();
                break;
            case 4:
                deleteById(scanner);
                break;
            default:
                system = false;
                break;
        }
    }

}
