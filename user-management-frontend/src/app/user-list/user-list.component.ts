// import { Component, OnInit } from '@angular/core';
// import { MatTableDataSource } from '@angular/material/table';
// import { Router } from '@angular/router';
// import { UserService } from '../user.service';
// import { User } from '../user.model';


// @Component({
//   selector: 'app-user-list',
//   templateUrl: './user-list.component.html',
//   styleUrls: ['./user-list.component.css']
// })
// export class UserListComponent implements OnInit {
//   users: MatTableDataSource<User> = new MatTableDataSource<User>(); 
//   displayedColumns: string[] = ['username', 'email', 'actions'];

//   constructor(private userService: UserService, private router: Router) {}

//   ngOnInit(): void {
//     this.userService.getAllUsers().subscribe(users => {
//       this.users = new MatTableDataSource(users);
//     });
//   }

//   createUser() {
//     this.router.navigate(['/user-form']);
//   }

//   editUser(id: number) {
//     this.router.navigate(['/user-form', id]);
//   }

//   deleteUser(id: number) {
//     this.userService.deleteUser(id).subscribe(() => {
//       this.users.data = this.users.data.filter(user => user.id !== id);
//     });
//   }

//   // deactivateUser(id: number) {
//   //   this.userService.deactivateUser(id).subscribe(deactivatedUser => {
//   //     const index = this.users.data.findIndex(user => user.id === deactivatedUser.id);
//   //     if (index !== -1) {
//   //       this.users.data[index] = deactivatedUser;
//   //       this.users._updateChangeSubscription(); // Update the table view
//   //     }
//   //   });
//   // }

//   toggleUserStatus(user: User) {
//     if (user.active) {
//       this.userService.deactivateUser(user.id).subscribe(deactivatedUser => {
//         const index = this.users.data.findIndex(u => u.id === deactivatedUser.id);
//         if (index !== -1) {
//           this.users.data[index] = deactivatedUser;
//           this.users._updateChangeSubscription(); // Update the table view
//         }
//       });
//     } else {
//       this.userService.activateUser(user.id).subscribe(activatedUser => {
//         const index = this.users.data.findIndex(u => u.id === activatedUser.id);
//         if (index !== -1) {
//           this.users.data[index] = activatedUser;
//           this.users._updateChangeSubscription(); // Update the table view
//         }
//       });
//     }
//   }
// }


import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user.model';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: MatTableDataSource<User> = new MatTableDataSource<User>(); 
  displayedColumns: string[] = ['username', 'email', 'actions'];

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe(users => {
      this.users = new MatTableDataSource(users);
    }, error => {
      console.error('Error loading users:', error);
    });
  }

  createUser() {
    this.router.navigate(['/user-form']);
  }

  editUser(id: number) {
    this.router.navigate(['/user-form', id]);
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id).subscribe(() => {
      this.users.data = this.users.data.filter(user => user.id !== id);
    }, error => {
      console.error('Error deleting user:', error);
    });
  }

  toggleUserStatus(user: User) {
    if (user.active) {
      this.userService.deactivateUser(user.id).subscribe(deactivatedUser => {
        const index = this.users.data.findIndex(u => u.id === deactivatedUser.id);
        if (index !== -1) {
          this.users.data[index] = deactivatedUser;
          this.users._updateChangeSubscription(); // Update the table view
        }
      }, error => {
        console.error('Error deactivating user:', error);
      });
    } else {
      this.userService.activateUser(user.id).subscribe(activatedUser => {
        const index = this.users.data.findIndex(u => u.id === activatedUser.id);
        if (index !== -1) {
          this.users.data[index] = activatedUser;
          this.users._updateChangeSubscription(); // Update the table view
        }
      }, error => {
        console.error('Error activating user:', error);
      });
    }
  }
}
