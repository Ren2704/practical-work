CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
    BEGIN
        NEW.updated_at = CURRENT_TIMESTAMP;
        RETURN NEW;
    END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER trg_academic_titles_updated_at
BEFORE UPDATE ON academic_titles
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_academic_degrees_updated_at
BEFORE UPDATE ON academic_degrees
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_education_subject_updated_at
BEFORE UPDATE ON education_subject
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_outstanding_people_updated_at
BEFORE UPDATE ON outstanding_people
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_job_title_updated_at
BEFORE UPDATE ON job_title
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_achievements_updated_at
BEFORE UPDATE ON achievements
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_person_job_updated_at
BEFORE UPDATE ON person_job
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_users_updated_at
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();