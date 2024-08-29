import { JobLink } from './job-link.model';
import { Technology } from './technology.model';

export class JobListing {
    uuid: string;
    title: string;
    jobLink: JobLink;
    technologies: Technology[];
}
