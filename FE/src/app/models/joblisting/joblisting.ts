import { JobLink } from '../joblink/joblink';
import { Technology } from '../technology/technology';

export class JobListing {
    uuid: string;
    title: string;
    jobLink: JobLink;
    technologies: Technology[];

    constructor(
        uuid: string,
        title: string,
        jobLink: JobLink,
        technologies: Technology[]
    ) {
        this.uuid = uuid;
        this.title = title;
        this.jobLink = jobLink;
        this.technologies = technologies;
    }
}
