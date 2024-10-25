-- Create the Candidate table
CREATE TABLE candidate (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    title VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(20),
    address TEXT,
    linked_in VARCHAR(255),
    website VARCHAR(255)
);

-- Create the Description table with a One-to-One relationship to the Candidate table
CREATE TABLE description (
    id BIGSERIAL PRIMARY KEY,
    description TEXT,
    candidate_id BIGINT,
    CONSTRAINT fk_description_candidate FOREIGN KEY (candidate_id) REFERENCES candidate (id) ON DELETE CASCADE
);

-- Create the Skills table with a Many-to-One relationship to the Candidate table
CREATE TABLE skills (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    candidate_id BIGINT,
    CONSTRAINT fk_skills_candidate FOREIGN KEY (candidate_id) REFERENCES candidate (id) ON DELETE CASCADE
);

-- Create the Skills element collection table to store the list of skills as strings
CREATE TABLE skills_list (
    skills_id BIGINT,
    skill VARCHAR(255),
    CONSTRAINT fk_skills_list_skills FOREIGN KEY (skills_id) REFERENCES skills (id) ON DELETE CASCADE
);

-- Create the Experiences table with a Many-to-One relationship to the Candidate table
CREATE TABLE experiences (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    sub_title VARCHAR(255),
    location VARCHAR(255),
    duration VARCHAR(255),
    candidate_id BIGINT,
    CONSTRAINT fk_experiences_candidate FOREIGN KEY (candidate_id) REFERENCES candidate (id) ON DELETE CASCADE
);

-- Create the Responsibilities element collection table to store the list of responsibilities as strings
CREATE TABLE experience_responsibilities (
    experience_id BIGINT,
    responsibility VARCHAR(255),
    CONSTRAINT fk_experience_responsibilities_experience FOREIGN KEY (experience_id) REFERENCES experiences (id) ON DELETE CASCADE
);

-- Create the Certificates table with a Many-to-One relationship to the Candidate table
CREATE TABLE certificates (
    id BIGSERIAL PRIMARY KEY,
    description TEXT,
    candidate_id BIGINT,
    CONSTRAINT fk_certificates_candidate FOREIGN KEY (candidate_id) REFERENCES candidate (id) ON DELETE CASCADE
);

-- Create the Projects table with a Many-to-One relationship to the Candidate table
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    duration VARCHAR(255),
    candidate_id BIGINT,
    CONSTRAINT fk_projects_candidate FOREIGN KEY (candidate_id) REFERENCES candidate (id) ON DELETE CASCADE
);

-- Create the Project Objectives element collection table to store the list of project objectives as strings
CREATE TABLE project_objectives (
    project_id BIGINT,
    objective TEXT,
    CONSTRAINT fk_project_objectives_project FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE
);

-- Create the Education table with a Many-to-One relationship to the Candidate table
CREATE TABLE education (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    candidate_id BIGINT,
    CONSTRAINT fk_education_candidate FOREIGN KEY (candidate_id) REFERENCES candidate (id) ON DELETE CASCADE
);

-- Creating prompt template
CREATE TABLE prompt_template (
    id BIGSERIAL PRIMARY KEY,
    template TEXT NOT NULL
);