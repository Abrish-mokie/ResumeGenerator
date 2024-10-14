-- Create the User table
CREATE TABLE "user" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    title VARCHAR(255)
);

-- Create the Description table with a One-to-One relationship to the User table
CREATE TABLE description (
    id BIGSERIAL PRIMARY KEY,
    description TEXT,
    user_id BIGINT,
    CONSTRAINT fk_description_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);

-- Create the Skills table with a Many-to-One relationship to the User table
CREATE TABLE skills (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    user_id BIGINT,
    CONSTRAINT fk_skills_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);

-- Create the Skills element collection table to store the list of skills as strings
CREATE TABLE skills_list (
    skills_id BIGINT,
    skill VARCHAR(255),
    CONSTRAINT fk_skills_list_skills FOREIGN KEY (skills_id) REFERENCES skills (id) ON DELETE CASCADE
);

-- Create the Experiences table with a Many-to-One relationship to the User table
CREATE TABLE experiences (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    sub_title VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT fk_experiences_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);

-- Create the Responsibilities element collection table to store the list of responsibilities as strings
CREATE TABLE experience_responsibilities (
    experience_id BIGINT,
    responsibility VARCHAR(255),
    CONSTRAINT fk_experience_responsibilities_experience FOREIGN KEY (experience_id) REFERENCES experiences (id) ON DELETE CASCADE
);

-- Create the Certificates table with a Many-to-One relationship to the User table
CREATE TABLE certificates (
    id BIGSERIAL PRIMARY KEY,
    description TEXT,
    user_id BIGINT,
    CONSTRAINT fk_certificates_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);

-- Create the Projects table with a Many-to-One relationship to the User table
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    user_id BIGINT,
    CONSTRAINT fk_projects_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);

-- Create the Project Objectives element collection table to store the list of project objectives as strings
CREATE TABLE project_objectives (
    project_id BIGINT,
    objective VARCHAR(255),
    CONSTRAINT fk_project_objectives_project FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE
);

-- Create the Education table with a Many-to-One relationship to the User table
CREATE TABLE education (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    user_id BIGINT,
    CONSTRAINT fk_education_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);

