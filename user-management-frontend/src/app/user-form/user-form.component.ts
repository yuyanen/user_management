// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { ActivatedRoute, Router } from '@angular/router';
// import { UserService } from '../user.service';
// import { User } from '../user.model';

// @Component({
//   selector: 'app-user-form',
//   templateUrl: './user-form.component.html',
//   styleUrls: ['./user-form.component.css']
// })
// export class UserFormComponent implements OnInit {
//   userForm: FormGroup;
//   isEditMode: boolean = false;
//   userId: number = 0;

//   constructor(
//     private fb: FormBuilder,
//     private userService: UserService,
//     private router: Router,
//     private route: ActivatedRoute
//   ) {
//     this.userForm = this.fb.group({
//       username: ['', Validators.required],
//       email: ['', [Validators.required, Validators.email]],
//       active: [true]
//     });
//   }

//   ngOnInit(): void {
//     this.route.params.subscribe(params => {
//       if (params['id']) {
//         this.isEditMode = true;
//         this.userId = params['id'];
//         this.userService.getUserById(this.userId).subscribe(user => {
//           this.userForm.patchValue(user);
//         });
//       }
//     });
//   }

//   onSubmit() {
//     if (this.userForm.invalid) {
//       return;
//     }

//     const user: User = this.userForm.value;
//     if (this.isEditMode) {
//       this.userService.updateUser(this.userId, user).subscribe(() => {
//         this.router.navigate(['/user-list']);
//       });
//     } else {
//       this.userService.createUser(user).subscribe(() => {
//         this.router.navigate(['/user-list']);
//       });
//     }
//   }
// }



import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user.model';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  userForm: FormGroup;
  isEditMode: boolean = false;
  userId: number = 0;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      active: [true]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.userId = params['id'];
        this.userService.getUserById(this.userId).subscribe(user => {
          this.userForm.patchValue(user);
        }, error => {
          console.error('Error fetching user:', error);
        });
      }
    });
  }

  onSubmit() {
    if (this.userForm.invalid) {
      return;
    }

    const user: User = this.userForm.value;
    if (this.isEditMode) {
      this.userService.updateUser(this.userId, user).subscribe(() => {
        this.router.navigate(['/user-list']);
      }, error => {
        console.error('Error updating user:', error);
      });
    } else {
      this.userService.createUser(user).subscribe(() => {
        this.router.navigate(['/user-list']);
      }, error => {
        console.error('Error creating user:', error);
      });
    }
  }
}
