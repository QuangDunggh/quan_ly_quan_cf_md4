class App {

    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: t,
                showConfirmButton: false,
                timer: 2500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                position: 'middle',
                icon: 'error',
                title: t,
                showConfirmButton: false,
                timer: 2500
            })
        }

        static ShowConfirmSuspend() {
            return Swal.fire({
                position: 'middle',
                icon: 'warning',
                title: "Are you sure suspend this client?",
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'No, cancel!',
                reverseButtons: true
            });
        }
    }

    static IziToast = class {
        static showSuccessAlert(t) {
            iziToast.show({
                title: 'Success',
                message: t,
                position: 'topRight',
                color: 'green',
                timeout: 2500,
            });
        }

        static showErrorAlert(t) {
            iziToast.show({
                title: 'Error',
                message: t,
                position: 'topRight',
                color: 'red',
                timeout: 2500,
            });
        }
    }
    
}

class Product{
    constructor(id,nameProduct,price,category,categoryName,description) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.category = category;
        this.categoryName = categoryName;
        this.description = description;
    }
}

class Category{
    constructor(id,name) {
        this.id = id;
        this.name = name;
    }
}