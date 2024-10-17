-- Add new columns "location" and "duration" to the Experiences table
ALTER TABLE experiences
ADD COLUMN location VARCHAR(255),
ADD COLUMN duration VARCHAR(255);