import { MobileNewComponent } from './components/mobile-new/mobile-new.component';
import { HomeComponent } from './components/home/home.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/compiler/src/core';
import { MobileListComponent } from './components/mobile-list/mobile-list.component';

export const ROUTES: Routes = [
    { path : '', component : HomeComponent },
    { path : 'mobile-new', component: MobileNewComponent },
    { path : 'mobile-list', component: MobileListComponent },
    { path : 'mobile-new/:id', component: MobileNewComponent }
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);