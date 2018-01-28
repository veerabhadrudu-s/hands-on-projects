/*
 * Author: sveera
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Router } from '@angular/router/src/router';
import { HeroesComponent } from '../heroes/components/heroes/heroes.component';
import { DashboardComponent } from '../heroes/components/dashboard/dashboard.component';
import { HeroDetailComponent } from '../heroes/components/hero-detail/hero-detail.component';
import { OnInit, OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'heroes', component: HeroesComponent },
  { path: 'details/:id', component: HeroDetailComponent }
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule implements OnInit, OnDestroy {
  constructor() {
    console.log(this.constructor.name + ' Instance created');
  }

  ngOnInit(): void {
    console.log(this.constructor.name + ' Instance initialized');
  }

  ngOnDestroy(): void {
    console.log(this.constructor.name + ' Instance destroyed ');
  }
}
