package com.alejandroLopez.service;

import com.alejandroLopez.model.Worker;
import com.alejandroLopez.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Optional<Worker> getWorkerById(Long id) {
        return workerRepository.findById(id);
    }

    public Worker createWorker(Worker worker) {
        // Hashear la contraseña antes de guardar
        worker.setPassword(passwordEncoder.encode(worker.getPassword()));
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Long id, Worker workerDetails) {
        return workerRepository.findById(id).map(worker -> {
            worker.setName(workerDetails.getName());
            worker.setSurname(workerDetails.getSurname());
            worker.setUsername(workerDetails.getUsername());
            worker.setCategory(workerDetails.getCategory());
            worker.setReports(workerDetails.getReports());
            worker.setInspections(workerDetails.getInspections());

            // Solo actualizar la contraseña si se proporciona una nueva
            if (workerDetails.getPassword() != null && !workerDetails.getPassword().isEmpty()) {
                worker.setPassword(passwordEncoder.encode(workerDetails.getPassword()));
            }

            return workerRepository.save(worker);
        }).orElseThrow(() -> new RuntimeException("Worker not found"));
    }

    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);
    }

    public Optional<Worker> findByUsername(String username) {
        return workerRepository.findByUsername(username);
    }


}
