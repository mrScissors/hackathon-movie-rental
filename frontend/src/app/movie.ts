export interface IMovie {
    id: string;
    title: string;
    genre: string[];
    releaseYear: number;
    artist: string[];
    director: string;
    rating: number;
    availableCopies: number;
    totalCopies: number;
}
