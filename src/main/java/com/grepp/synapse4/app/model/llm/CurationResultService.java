package com.grepp.synapse4.app.model.llm;

import com.grepp.synapse4.app.model.llm.dto.AdminCurationResultDto;
import com.grepp.synapse4.app.model.llm.repository.CurationResultRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CurationResultService {

    private final CurationResultRepository repository;
//    private EntityManager entityManager;

    public List<AdminCurationResultDto> getResultsByCurationId() {
        return repository.findResultsByCurationId();
    }

//    // Dirty-Checking 방식
//    public void toggleActive(Long id) {
//        CurationResult cr = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("CurationResult not found: " + id));
//        cr.setActive(!cr.isActive());
//        repository.flush();
//        entityManager.clear();
//    }

    // 쿼리 modify 방식
    @Transactional
    public void toggleActive(Long id) {

        // select 를 해서 update 가능한지
        // 가능 -> update 수행
        // transaction 비용 이슈

        int updated = repository.toggleActiveById(id);
        if (updated == 0) {
            throw new EntityNotFoundException("큐레이션 결과가 없습니다.");
        }
    }
}




