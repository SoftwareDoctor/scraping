import { Technology } from '../technology/technology';

export class JobListing {
    uuid: string;
    title: string;
    jobLink: string;  // Cambiato da JobLink a string
    technologies: Technology[];

    constructor(
        uuid: string,
        title: string,
        jobLink: string,  // Cambiato da JobLink a string
        technologies: Technology[]
    ) {
        this.uuid = uuid;
        this.title = title;
        this.jobLink = jobLink;  // Cambiato da JobLink a string
        this.technologies = technologies;
    }
}
