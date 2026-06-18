package com.example.demo.cleanup;

import com.example.demo.dao.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CleanupService {

    private final AchievementRepository achievementRepository;
    private final PersonJobRepository personJobRepository;
    private final OutstandingPersonRepository outstandingPersonRepository;
    private final AcademicTitleRepository academicTitleRepository;
    private final AcademicDegreeRepository academicDegreeRepository;
    private final EducationSubjectRepository educationSubjectRepository;
    private final JobTitleRepository jobTitleRepository;

    @Transactional
    public void clearingDeletedRecords() {

        OffsetDateTime threshold = OffsetDateTime.now().minusMonths(6);

        achievementRepository.deleteOldRecords(threshold);
        personJobRepository.deleteOldRecords(threshold);
        outstandingPersonRepository.deleteOldRecords(threshold);
        academicTitleRepository.deleteOldRecords(threshold);
        academicDegreeRepository.deleteOldRecords(threshold);
        educationSubjectRepository.deleteOldRecords(threshold);
        jobTitleRepository.deleteOldRecords(threshold);
    }
}