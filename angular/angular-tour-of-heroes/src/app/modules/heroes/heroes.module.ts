/*
 * Author: sveera
 */
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HeroesComponent } from './components/heroes/heroes.component';
import { HeroDetailComponent } from './components/hero-detail/hero-detail.component';
import { HeroService } from '../services/hero.service';
import { MessagesComponent } from './components/messages/messages.component';
import { MessageService } from '../services/message.service';
import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AppRoutingModule } from '../app-routing/app-routing.module';
import { OnInit, OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';


@NgModule({
    declarations: [
        HeroesComponent,
        HeroDetailComponent,
        MessagesComponent,
        DashboardComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule
    ], exports: [
        MessagesComponent,
    ], providers: [
        HeroService,
        MessageService
    ]
})
export class HeroesModule implements OnInit, OnDestroy {

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
