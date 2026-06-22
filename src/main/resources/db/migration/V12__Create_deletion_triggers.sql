CREATE OR REPLACE FUNCTION set_deleted_at()
RETURNS TRIGGER AS $$
    BEGIN
        IF OLD.is_deleted = false AND NEW.is_deleted = true THEN
            NEW.deleted_at = CURRENT_TIMESTAMP;
        ELSIF OLD.is_deleted = true AND NEW.is_deleted = false THEN
            NEW.deleted_at = NULL;
        END IF;
        RETURN NEW;
    END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER trg_academic_titles_deleted_at
BEFORE UPDATE OF is_deleted ON academic_titles
FOR EACH ROW
EXECUTE FUNCTION set_deleted_at();

CREATE TRIGGER trg_academic_degrees_deleted_at
    BEFORE UPDATE OF is_deleted ON academic_degrees
    FOR EACH ROW
EXECUTE FUNCTION set_deleted_at();

CREATE TRIGGER trg_education_subject_deleted_at
    BEFORE UPDATE OF is_deleted ON education_subject
    FOR EACH ROW
EXECUTE FUNCTION set_deleted_at();

CREATE TRIGGER trg_outstanding_people_deleted_at
    BEFORE UPDATE OF is_deleted ON outstanding_people
    FOR EACH ROW
EXECUTE FUNCTION set_deleted_at();

CREATE TRIGGER trg_job_title_deleted_at
    BEFORE UPDATE OF is_deleted ON job_title
    FOR EACH ROW
EXECUTE FUNCTION set_deleted_at();

CREATE TRIGGER trg_achievements_deleted_at
    BEFORE UPDATE OF is_deleted ON achievements
    FOR EACH ROW
EXECUTE FUNCTION set_deleted_at();

CREATE TRIGGER trg_person_job_deleted_at
    BEFORE UPDATE OF is_deleted ON person_job
    FOR EACH ROW
EXECUTE FUNCTION set_deleted_at();