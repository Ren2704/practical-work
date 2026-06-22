CREATE INDEX idx_outstanding_people_deleted_surname
    ON outstanding_people (is_deleted, surname);

CREATE INDEX idx_outstanding_people_academic_title
    ON outstanding_people (id_academic_title);

CREATE INDEX idx_outstanding_people_academic_degree
    ON outstanding_people (id_academic_degree);

CREATE INDEX idx_outstanding_people_education_subject
    ON outstanding_people (id_education_subject);

CREATE INDEX idx_outstanding_people_deleted_at
    ON outstanding_people(deleted_at);

CREATE INDEX idx_achievements_person_deleted_year
    ON achievements (id_person, is_deleted, year DESC);

CREATE INDEX idx_achievements_deleted_at
    ON achievements(deleted_at);

CREATE INDEX idx_person_job_person_deleted_start
    ON person_job (id_person, is_deleted, start_year DESC);

CREATE INDEX idx_person_job_job_title
    ON person_job (id_job_title);

CREATE INDEX idx_person_job_current
    ON person_job (id_person)
    WHERE is_current = true AND is_deleted = false;

CREATE INDEX idx_person_job_deleted_at
    ON person_job(deleted_at);