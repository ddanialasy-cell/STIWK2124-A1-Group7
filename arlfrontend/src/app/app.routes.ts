import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Book } from './book/book';
import { AddBook } from './add-book/add-book';
import { RemoveBook } from './remove-book/remove-book';
import { EditBook } from './edit-book/edit-book';
import { Navbar } from './navbar/navbar'; 
import { Library } from './library/library';

export const routes: Routes = [
    {path: '', component: Navbar, 
        children: [{path: '', component: Home},{path: 'library', component: Library},{path: 'book', component: Book,  children: 
            [{path: 'add', component: AddBook}, {path: 'remove', component: RemoveBook}, {path: 'edit', component: EditBook}]
        }]},
    //{path: 'books', component: Books}

    //{path: 'book' , component: Books, children: [{path: 'add', component: AddBook}, {path: 'remove', component: RemoveBook}, {path: 'edit', component: EditBook}]}
];
